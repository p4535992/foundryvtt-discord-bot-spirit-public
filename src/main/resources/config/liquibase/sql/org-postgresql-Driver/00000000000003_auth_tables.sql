-- =====================================================================
-- 02_authorization.sql
-- =====================================================================
-- SCOPO
--   Questo script crea lo schema "Authorization" completo:
--     - Utenti (local password opzionale) + tenant/realm
--     - Identità OIDC (Keycloak/Okta) collegate agli utenti
--     - Ruoli e permessi (wildcard style Shiro)
--     - Mapping utenti<->ruoli e ruoli<->permessi
--     - MFA TOTP (secret cifrato), recovery codes, trusted devices
--     - Audit log eventi sicurezza
--
-- COMPATIBILITÀ SHIRO (DEFAULT, SENZA QUERY CUSTOM)
--   Apache Shiro JdbcRealm, se lasci le query di default, si aspetta:
--     users(username, password, password_salt)
--     user_roles(username, role_name)
--     roles_permissions(role_name, permission)
--   Per questo creiamo le VIEW con questi NOMI ESATTI.
--   NON rinominare users/user_roles/roles_permissions se vuoi restare "default".
--
-- NOTE OPERATIVE (IMPORTANTI)
--   1) password_hash NON è mai password in chiaro: contiene solo hash sicuro.
--   2) password_salt è opzionale:
--        - spesso NULL con bcrypt/argon2 self-contained
--        - utile se vuoi salt separato (saltStyle=COLUMN lato Shiro)
--   3) OIDC-only: password_hash può essere NULL (autenticazione delegata a IdP).
--   4) MFA: secret_enc deve essere cifrato a riposo (a livello app, es. AES-GCM).
--   5) trusted devices: salvare solo hash token, mai il token in chiaro.
-- =====================================================================

BEGIN;

-- =====================================================================
-- TABLE: app_users
-- =====================================================================
-- Contiene gli utenti interni dell'applicazione.
-- Include:
--   - tenant (realm) per segregare utenti/config (Keycloak realms)
--   - credenziali locali opzionali (password_hash/password_salt)
--   - auth_source per distinguere utenti LOCAL/OIDC/BOTH
--   - flag force_password_change per password temporanee/reset admin
-- =====================================================================
CREATE TABLE app_users (
  id BIGSERIAL PRIMARY KEY,

  -- Identificativo utente (login). Unico a livello DB.
  username VARCHAR(255) UNIQUE NOT NULL,

  -- Tenant/realm (Keycloak realm). Usato per isolare utenti e config.
  tenant VARCHAR(100) NOT NULL,

  -- Credenziali locali (opzionali se OIDC-only)
  password_hash VARCHAR(255),
  password_salt VARCHAR(255),

  -- Origine autenticazione:
  --   LOCAL = solo password DB
  --   OIDC  = solo IdP (Keycloak/Okta)
  --   BOTH  = entrambi possibili
  auth_source VARCHAR(10) NOT NULL DEFAULT 'BOTH',

  -- Abilitazione account
  enabled BOOLEAN NOT NULL DEFAULT TRUE,

  -- Se true: forza cambio password al prossimo login locale (reset admin)
  force_password_change BOOLEAN NOT NULL DEFAULT FALSE,

  -- Dati profilo (opzionali)
  email VARCHAR(320),
  email_verified BOOLEAN NOT NULL DEFAULT FALSE,

  -- Timestamp audit base
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

  -- Vincoli di consistenza
  CONSTRAINT chk_auth_source CHECK (auth_source IN ('LOCAL','OIDC','BOTH')),
  -- Se LOCAL-only, la password deve esistere
  CONSTRAINT chk_local_requires_password CHECK (auth_source <> 'LOCAL' OR password_hash IS NOT NULL)
);

COMMENT ON TABLE app_users IS
  'Utenti applicativi normalizzati. Tenant/realm obbligatorio. Password locale opzionale per OIDC-only.';

COMMENT ON COLUMN app_users.tenant IS
  'Tenant/realm (Keycloak). Separazione logica utenti/config.';

COMMENT ON COLUMN app_users.password_hash IS
  'Hash password (mai password in chiaro). NULL per utenti OIDC-only.';

COMMENT ON COLUMN app_users.password_salt IS
  'Salt opzionale (tipicamente NULL con bcrypt/argon2 self-contained; utile per saltStyle=COLUMN in Shiro).';

COMMENT ON COLUMN app_users.auth_source IS
  'Origine autenticazione: LOCAL, OIDC, BOTH.';

COMMENT ON COLUMN app_users.force_password_change IS
  'Se true, al prossimo login locale l’utente deve cambiare password (password temporanea).';

CREATE INDEX idx_app_users_username ON app_users(username);
CREATE INDEX idx_app_users_tenant ON app_users(tenant);
CREATE INDEX idx_app_users_enabled ON app_users(enabled);


-- =====================================================================
-- TABLE: app_user_oidc_identities
-- =====================================================================
-- Collega un utente interno (app_users) a una identità esterna OIDC:
--   - issuer: identifica l'authorization server / realm
--   - subject: claim "sub", stabile e unico per issuer
-- Include tenant per coerenza con realms, e per query più rapide.
-- =====================================================================
CREATE TABLE app_user_oidc_identities (
  id BIGSERIAL PRIMARY KEY,

  -- FK verso utente interno
  user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,

  -- Informazioni IdP
  provider VARCHAR(50) NOT NULL,  -- es: 'keycloak', 'okta', 'oidc'
  issuer TEXT NOT NULL,           -- OIDC issuer URL
  subject TEXT NOT NULL,          -- claim 'sub'
  preferred_username TEXT,
  email VARCHAR(320),

  -- Tenant/realm (di solito coincide con realm/issuer, qui esplicito)
  tenant VARCHAR(100) NOT NULL,

  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  last_login_at TIMESTAMPTZ,

  -- Unicità: una identità esterna mappa 1 utente interno
  UNIQUE (issuer, subject),
  UNIQUE (provider, issuer, subject)
);

COMMENT ON TABLE app_user_oidc_identities IS
  'Mapping identità OIDC (issuer+sub) -> utente interno. Supporta Keycloak/Okta.';

COMMENT ON COLUMN app_user_oidc_identities.issuer IS
  'OIDC issuer (URL). Cambia per realm/authorization server.';

COMMENT ON COLUMN app_user_oidc_identities.subject IS
  'OIDC sub (subject). Identificativo stabile per issuer.';

CREATE INDEX idx_oidc_identities_user_id ON app_user_oidc_identities(user_id);
CREATE INDEX idx_oidc_identities_tenant ON app_user_oidc_identities(tenant);


-- =====================================================================
-- TABLE: app_roles
-- =====================================================================
-- Ruoli applicativi.
-- Include "priority" per stabilire precedenza tra ruoli quando risolvi
-- configurazioni/override (es. properties) o policy.
-- =====================================================================
CREATE TABLE app_roles (
  id BIGSERIAL PRIMARY KEY,

  -- Nome ruolo (es: 'admin', 'operator', 'viewer')
  name VARCHAR(255) UNIQUE NOT NULL,

  description TEXT,

  -- Precedenza: maggiore = più importante
  priority INTEGER NOT NULL DEFAULT 0,

  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

COMMENT ON TABLE app_roles IS
  'Ruoli applicativi. priority serve per determinare precedenza tra ruoli (più alto = più importante).';

CREATE INDEX idx_app_roles_priority ON app_roles(priority);


-- =====================================================================
-- TABLE: app_permissions
-- =====================================================================
-- Permessi (wildcard style) in stile Apache Shiro:
--   es: "user:create", "document:*", "report:read"
-- =====================================================================
CREATE TABLE app_permissions (
  id BIGSERIAL PRIMARY KEY,
  permission VARCHAR(255) UNIQUE NOT NULL,
  description TEXT
);

COMMENT ON TABLE app_permissions IS
  'Permessi wildcard (stile Shiro). Esempi: user:create, document:*, report:read.';


-- =====================================================================
-- TABLE: app_user_roles
-- =====================================================================
-- Associazione N:M utenti <-> ruoli.
-- =====================================================================
CREATE TABLE app_user_roles (
  user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES app_roles(id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  PRIMARY KEY (user_id, role_id)
);

COMMENT ON TABLE app_user_roles IS
  'Associazione N:M tra utenti e ruoli.';

CREATE INDEX idx_app_user_roles_user_id ON app_user_roles(user_id);
CREATE INDEX idx_app_user_roles_role_id ON app_user_roles(role_id);


-- =====================================================================
-- TABLE: app_role_permissions
-- =====================================================================
-- Associazione N:M ruoli <-> permessi.
-- =====================================================================
CREATE TABLE app_role_permissions (
  role_id BIGINT NOT NULL REFERENCES app_roles(id) ON DELETE CASCADE,
  permission_id BIGINT NOT NULL REFERENCES app_permissions(id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  PRIMARY KEY (role_id, permission_id)
);

COMMENT ON TABLE app_role_permissions IS
  'Associazione N:M tra ruoli e permessi.';

-- Indici importanti per join/lookup
CREATE INDEX idx_app_role_permissions_role_id ON app_role_permissions(role_id);
CREATE INDEX idx_app_role_permissions_permission_id ON app_role_permissions(permission_id);


-- =====================================================================
-- TABLE: app_user_mfa_totp
-- =====================================================================
-- MFA TOTP (Google Authenticator):
--   - secret_enc: segreto cifrato (BYTEA), mai in chiaro
--   - enabled: true solo dopo enrollment verificato
--   - failed_attempts/locked_until: anti brute-force OTP
-- =====================================================================
CREATE TABLE app_user_mfa_totp (
  user_id BIGINT PRIMARY KEY REFERENCES app_users(id) ON DELETE CASCADE,

  -- Segreto TOTP cifrato a riposo
  secret_enc BYTEA NOT NULL,

  enabled BOOLEAN NOT NULL DEFAULT FALSE,
  enrolled_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  verified_at TIMESTAMPTZ,

  last_used_at TIMESTAMPTZ,
  last_failed_at TIMESTAMPTZ,

  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

  failed_attempts INTEGER NOT NULL DEFAULT 0,
  locked_until TIMESTAMPTZ
);

COMMENT ON TABLE app_user_mfa_totp IS
  'MFA TOTP per utente. secret_enc cifrato. enabled true dopo verifica. Include lock anti brute-force.';

CREATE INDEX idx_mfa_totp_enabled ON app_user_mfa_totp(enabled);
CREATE INDEX idx_mfa_totp_locked_until ON app_user_mfa_totp(locked_until);


-- =====================================================================
-- TABLE: app_user_mfa_recovery_codes
-- =====================================================================
-- Recovery codes:
--   - salvare SOLO hash dei codici
--   - marcare used_at quando consumato
-- =====================================================================
CREATE TABLE app_user_mfa_recovery_codes (
  user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,
  code_hash TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  used_at TIMESTAMPTZ,
  PRIMARY KEY (user_id, code_hash)
);

COMMENT ON TABLE app_user_mfa_recovery_codes IS
  'Recovery codes MFA: memorizzare solo hash; used_at quando usato.';

CREATE INDEX idx_recovery_codes_user_id ON app_user_mfa_recovery_codes(user_id);
CREATE INDEX idx_recovery_codes_used_at ON app_user_mfa_recovery_codes(used_at);


-- =====================================================================
-- TABLE: app_user_trusted_devices
-- =====================================================================
-- Trusted devices:
--   - memorizzare solo hash del token (device_hash), mai token in chiaro
--   - scadenza (expires_at) per invalidazione automatica
-- =====================================================================
CREATE TABLE app_user_trusted_devices (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,

  device_label TEXT,

  -- Hash del token
  device_hash TEXT NOT NULL,

  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  last_used_at TIMESTAMPTZ,
  expires_at TIMESTAMPTZ NOT NULL,

  UNIQUE (user_id, device_hash)
);

COMMENT ON TABLE app_user_trusted_devices IS
  'Trusted devices: memorizzare solo hash token e scadenza; utile per bypass MFA su device fidati.';

CREATE INDEX idx_trusted_devices_user_id ON app_user_trusted_devices(user_id);
CREATE INDEX idx_trusted_devices_expires ON app_user_trusted_devices(expires_at);


-- =====================================================================
-- TABLE: app_audit_events
-- =====================================================================
-- Audit log eventi sicurezza:
--   - login/logout
--   - callback OIDC
--   - verify MFA (SUCCESS/FAIL)
--   - authz deny
-- detail JSONB per contesti extra (es. permission richiesta, error code, ecc.)
-- =====================================================================
CREATE TABLE app_audit_events (
  id BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

  user_id BIGINT REFERENCES app_users(id) ON DELETE SET NULL,
  username VARCHAR(255),

  oidc_issuer TEXT,
  oidc_subject TEXT,

  event_type VARCHAR(50) NOT NULL,
  outcome VARCHAR(20) NOT NULL,
  reason TEXT,

  session_id TEXT,
  request_id TEXT,
  ip_addr INET,
  user_agent TEXT,

  detail JSONB,

  CONSTRAINT chk_audit_outcome CHECK (outcome IN ('SUCCESS','FAIL','DENY'))
);

COMMENT ON TABLE app_audit_events IS
  'Audit eventi sicurezza (login/logout/oidc/mfa/authz). outcome: SUCCESS/FAIL/DENY.';

CREATE INDEX idx_audit_created_at ON app_audit_events(created_at);
CREATE INDEX idx_audit_user_id ON app_audit_events(user_id);
CREATE INDEX idx_audit_event_type ON app_audit_events(event_type);
CREATE INDEX idx_audit_outcome ON app_audit_events(outcome);
CREATE INDEX idx_audit_oidc_sub ON app_audit_events(oidc_subject);
CREATE INDEX idx_audit_detail_gin ON app_audit_events USING GIN (detail);


-- =====================================================================
-- VIEW SHIRO DEFAULT CONTRACT (NON rinominare)
-- =====================================================================
-- Queste VIEW permettono a Shiro JdbcRealm di usare le query di default.
-- Se le rinomini, devi configurare query custom nel realm.
-- =====================================================================

-- VIEW: users(username, password, password_salt)
CREATE VIEW users AS
SELECT
  u.username,
  u.password_hash AS password,
  u.password_salt
FROM app_users u
WHERE u.enabled = TRUE;

COMMENT ON VIEW users IS
  'Shiro default: users(username,password,password_salt). password è hash.';

-- VIEW: user_roles(username, role_name)
CREATE VIEW user_roles AS
SELECT
  u.username,
  r.name AS role_name
FROM app_user_roles ur
JOIN app_users u ON u.id = ur.user_id
JOIN app_roles r ON r.id = ur.role_id
WHERE u.enabled = TRUE;

COMMENT ON VIEW user_roles IS
  'Shiro default: user_roles(username,role_name).';

-- VIEW: roles_permissions(role_name, permission)
CREATE VIEW roles_permissions AS
SELECT
  r.name AS role_name,
  p.permission
FROM app_role_permissions rp
JOIN app_roles r ON r.id = rp.role_id
JOIN app_permissions p ON p.id = rp.permission_id;

COMMENT ON VIEW roles_permissions IS
  'Shiro default: roles_permissions(role_name,permission).';


-- =====================================================================
-- VIEW EXTRA (prefisso v_) - Reporting / Debug
-- =====================================================================

-- VIEW: v_auth_user_role_permission_rows_loose
-- Include anche utenti senza ruoli e/o permessi.
-- Output: N righe per username, con role_name/permission eventualmente NULL.
CREATE VIEW v_auth_user_role_permission_rows_loose AS
SELECT
  u.id AS user_id,
  u.username AS username,
  u.tenant AS tenant,
  u.enabled AS user_enabled,
  u.auth_source AS auth_source,
  u.force_password_change AS force_password_change,

  r.id AS role_id,
  r.name AS role_name,

  p.id AS permission_id,
  p.permission AS permission
FROM app_users u
LEFT JOIN app_user_roles ur ON ur.user_id = u.id
LEFT JOIN app_roles r ON r.id = ur.role_id
LEFT JOIN app_role_permissions rp ON rp.role_id = r.id
LEFT JOIN app_permissions p ON p.id = rp.permission_id
WHERE u.enabled = TRUE;

COMMENT ON VIEW v_auth_user_role_permission_rows_loose IS
  'Reporting: righe user-role-permission includendo utenti senza ruoli/permessi (LEFT JOIN).';

-- VIEW: v_auth_user_role_permission_rows_shiro
-- Solo combinazioni complete (username, role, permission).
-- Output: N righe per username = (ruoli * permessi per ruolo).
CREATE VIEW v_auth_user_role_permission_rows_shiro AS
SELECT
  u.username AS username,
  u.tenant AS tenant,
  r.name AS role_name,
  p.permission AS permission
FROM app_users u
JOIN app_user_roles ur ON ur.user_id = u.id
JOIN app_roles r ON r.id = ur.role_id
JOIN app_role_permissions rp ON rp.role_id = r.id
JOIN app_permissions p ON p.id = rp.permission_id
WHERE u.enabled = TRUE;

COMMENT ON VIEW v_auth_user_role_permission_rows_shiro IS
  'Strict: righe (username, role_name, permission) solo complete (JOIN).';

-- VIEW: v_auth_user_effective_authorizations
-- Aggregata: 1 riga per utente con array di ruoli e permessi.
CREATE VIEW v_auth_user_effective_authorizations AS
SELECT
  u.id AS user_id,
  u.username AS username,
  u.tenant AS tenant,
  u.enabled AS user_enabled,
  u.auth_source AS auth_source,
  u.email AS email,

  COALESCE((
    SELECT array_agg(DISTINCT r.name ORDER BY r.name)
    FROM app_user_roles ur
    JOIN app_roles r ON r.id = ur.role_id
    WHERE ur.user_id = u.id
  ), ARRAY[]::TEXT[]) AS roles,

  COALESCE((
    SELECT array_agg(DISTINCT p.permission ORDER BY p.permission)
    FROM app_user_roles ur
    JOIN app_role_permissions rp ON rp.role_id = ur.role_id
    JOIN app_permissions p ON p.id = rp.permission_id
    WHERE ur.user_id = u.id
  ), ARRAY[]::TEXT[]) AS permissions

FROM app_users u
WHERE u.enabled = TRUE;

COMMENT ON VIEW v_auth_user_effective_authorizations IS
  'Aggregata: 1 riga per utente con roles[] e permissions[] (effective authorizations).';


-- =====================================================================
-- ESEMPI RAPIDI (commenti)
-- =====================================================================
-- 1) Shiro auth (default):
--    SELECT password, password_salt FROM users WHERE username = ?
--
-- 2) Shiro roles:
--    SELECT role_name FROM user_roles WHERE username = ?
--
-- 3) Shiro permissions:
--    SELECT permission FROM roles_permissions WHERE role_name = ?
--
-- 4) Debug (strict):
--    SELECT * FROM v_auth_user_role_permission_rows_shiro WHERE username='mario';
--
-- 5) Debug (loose):
--    SELECT * FROM v_auth_user_role_permission_rows_loose WHERE username='mario';
--
-- 6) Aggregato:
--    SELECT * FROM v_auth_user_effective_authorizations WHERE username='mario';
-- =====================================================================

COMMIT;
