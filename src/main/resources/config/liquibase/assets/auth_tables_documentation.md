# Flow completo Shiro: OIDC → Subject → MFA step-up → Autorizzazione

Questo documento descrive un’implementazione tipica con:

- **SSO OIDC** (Keycloak/Okta) per l’autenticazione primaria
- **MFA TOTP** (Google Authenticator) come “step-up”
- **Apache Shiro** per sessione, subject e autorizzazione (ruoli/permessi)
- **DB schema**: tabelle normalizzate `app_*` + **VIEW compatibili** con `JdbcRealm`

---

## 1) Componenti (a blocchi)

- **OIDC Client** (nella tua app): gestisce redirect, code exchange, validazione token (ID token/JWT).
- **Identity Provider (IdP)**: Keycloak/Okta.
- **Shiro Subject**: rappresenta l’utente nel runtime (sessione, principal, ruoli/permessi).
- **Realm/i Shiro**:
  - Realm OIDC (custom): “autentica” l’utente tramite token OIDC validato.
  - JdbcRealm (o realm custom): carica **ruoli/permessi** dal DB (via VIEW `user_roles`, `roles_permissions`).
- **MFA Service** (custom): verifica TOTP/recovery codes + trusted devices.

> Nota: Shiro non fornisce “MFA integrata” nativa per OIDC/TOTP: lo step-up è design applicativo.

---

## 2) OIDC → callback → mappatura utente

### 2.1 Redirect login
1. Utente clicca “Login”.
2. App fa redirect a IdP (Keycloak/Okta) con Authorization Code Flow (consigliato con PKCE).
3. IdP autentica e ritorna alla tua **redirect_uri** con `code`.

### 2.2 Callback: exchange e validazione token
4. App scambia `code` con `access_token` + `id_token` (JWT).
5. App valida **id_token**:
   - firma (JWK), issuer, audience, exp, nonce (se usi), ecc.
6. Estrae le claims principali:
   - `iss` (issuer), `sub` (subject), `preferred_username`, `email`, ecc.

### 2.3 Mappatura su utente interno
7. Cerca in DB `app_user_oidc_identities` per `(issuer, subject)`.
   - Se esiste: ottieni `user_id`.
   - Se non esiste: crea utente in `app_users` + identity in `app_user_oidc_identities`.
8. Aggiorna `last_login_at` e, opzionalmente, email/username.

**Audit consigliato**
- Inserisci in `app_audit_events` un evento:
  - `event_type = 'OIDC_CALLBACK'`
  - `outcome = 'SUCCESS'` o `FAIL`
  - `oidc_issuer`, `oidc_subject`, `ip_addr`, `user_agent`

---

## 3) Creazione del Subject Shiro (autenticazione primaria OIDC)

### Approccio consigliato
- Crea un **AuthenticationToken custom**, ad es. `OidcToken(issuer, subject, username, claims...)`.
- Implementa un **Realm custom** che:
  1. accetta `OidcToken`
  2. considera l’utente “autenticato” perché il token è già stato validato lato OIDC client
  3. ritorna un `AuthenticationInfo` con principal = (ad es.) `username` o un oggetto `UserPrincipal`

### Perché così
- Eviti di “forzare” JdbcRealm per autenticare utenti SSO-only (che non hanno password).
- Lasci a JdbcRealm (o a un AuthorizationRealm custom) la parte **ruoli/permessi**.

**Audit consigliato**
- `event_type = 'AUTH_LOGIN'` (primario)
- `outcome = 'SUCCESS'` o `FAIL'`

---

## 4) MFA step-up (TOTP) dopo OIDC

Dopo che il Subject è autenticato via OIDC, puoi richiedere MFA in base a policy.

### 4.1 Decisione “Serve MFA?”
Esempi di policy (tipiche):
- MFA abilitata per l’utente (`app_user_mfa_totp.enabled = true`)
- non è un “trusted device” valido e non scaduto
- operazione sensibile (step-up per azioni ad alto rischio)
- contesto sospetto (nuovo IP, nuovo device, ecc.)

### 4.2 Stato “parzialmente autenticato”
Due strategie comuni:

**A) Flag in sessione (semplice)**
- Dopo login OIDC: metti in sessione `mfa_verified = false`.
- Permetti accesso solo a endpoint “MFA challenge/verify”.
- Dopo verifica TOTP: set `mfa_verified = true`.

**B) Principals/roles temporanei**
- Fino a MFA, assegni un ruolo “preauth” o usi un principal che non passa i check.
- Dopo MFA, “upgrade” del subject (di solito con session attribute + re-check).

> In Shiro, è comune usare la session per gestire questo “step-up”, perché il Subject è già autenticato.

### 4.3 Verifica TOTP
1. L’utente inserisce codice OTP.
2. Recuperi `app_user_mfa_totp.secret_enc` e lo decifri a livello app.
3. Verifichi il TOTP (con window toleranza minima, es. ±1 step).
4. Aggiorni:
   - `failed_attempts`, `locked_until` se errori ripetuti
   - `verified_at` e `enabled` (se era enrollment)
5. Metti `mfa_verified = true` in sessione.

**Audit consigliato**
- `event_type = 'MFA_VERIFY'`, outcome SUCCESS/FAIL
- su FAIL: reason + incremento contatori

### 4.4 Recovery codes (fallback)
- Confronti il codice inserito con gli hash in `app_user_mfa_recovery_codes` (solo non usati).
- Se match:
  - set `used_at = now()`
  - `mfa_verified = true`

**Audit**
- `event_type = 'MFA_RECOVERY'`

### 4.5 Trusted device
- Se l’utente spunta “ricorda questo dispositivo”:
  - generi un token random
  - salvi **hash(token)** in `app_user_trusted_devices.device_hash` con scadenza
  - metti il token in cookie sicuro (HttpOnly, Secure, SameSite)
- Alla richiesta successiva, se il cookie è valido e non scaduto, puoi saltare MFA.

---

## 5) Autorizzazione (ruoli/permessi) con Shiro

### 5.1 Ruoli/permessi dal DB
Hai due opzioni:

**Opzione 1 (pratica): VIEW + JdbcRealm**
- Usi le VIEW:
  - `users(username,password,password_salt)` (per password login se ti serve)
  - `user_roles(username, role_name)`
  - `roles_permissions(role_name, permission)`
- JdbcRealm può caricare ruoli e permessi senza join custom.

**Opzione 2: Realm di autorizzazione custom**
- Se vuoi logiche extra (tenant, policy dinamiche) implementi `AuthorizationInfo` tu.

### 5.2 Gate MFA (step-up) nelle regole
Tipico pattern:
- per endpoint protetti: richiedi *sia* permessi *sia* `mfa_verified=true`
- implementabile in:
  - un filtro Shiro custom (consigliato) che controlla session attribute
  - oppure in interceptors / annotations (prima di `@RequiresPermissions` o insieme)

**Esempio logico**
- `if subject.isAuthenticated() && session.mfa_verified == true && subject.isPermitted("admin:*")`

**Audit**
- quando un check nega accesso: `event_type='AUTHZ_CHECK'`, `outcome='DENY'`, reason + permission richiesta

---

## 6) Logout
- Logout locale: `Subject.logout()` → invalidazione sessione Shiro.
- Logout SSO: opzionale (dipende dall’IdP), spesso con endpoint di logout OIDC (front-channel/back-channel).
- Audit: `event_type='AUTH_LOGOUT'`.

---

## 7) Eventi audit suggeriti (event_type)

- `OIDC_REDIRECT` (opzionale)
- `OIDC_CALLBACK`
- `AUTH_LOGIN` (SSO)
- `MFA_CHALLENGE` (quando chiedi OTP)
- `MFA_VERIFY`
- `MFA_RECOVERY`
- `TRUST_DEVICE_ISSUE` / `TRUST_DEVICE_USE`
- `AUTHZ_CHECK` (DENY) per accessi negati
- `AUTH_LOGOUT`

---

## 8) Checklist sicurezza (breve)

- Validazione JWT rigorosa (issuer/audience/exp/nonce).
- Segreto TOTP cifrato a riposo + chiavi gestite bene.
- Rate limit su OTP + lock temporaneo.
- Cookie trusted device: Secure + HttpOnly + SameSite, rotazione e scadenze.
- Audit su eventi FAIL/DENY con correlation id (request_id).
