-- =====================================================================
-- 01_properties.sql
-- =====================================================================
-- SCOPO
--   Gestione proprietà applicative in stile key/value con:
--     - scope UTENTE / RUOLO / GLOBALE
--     - tenant/realm (Keycloak) per differenziare configurazioni tra realms
--     - precedenza deterministica:
--         USER > ROLE(priority) > GLOBAL
--       e, per tenant:
--         tenant specifico > tenant NULL (fallback cross-tenant)
--     - trigger per aggiornare updated_at automaticamente
--
-- REQUISITI
--   Deve esistere lo schema authorization:
--     app_users (con colonna tenant)
--     app_roles (con colonna priority)
--     app_user_roles
--
-- NOTE OPERATIVE
--   - tenant NULL = proprietà cross-tenant (fallback). Se non ti serve, puoi vietarlo.
--   - in caso di più ruoli con la stessa property_key:
--       vince il ruolo con priority più alta
-- =====================================================================

BEGIN;

-- =====================================================================
-- TABLE: app_properties
-- =====================================================================
-- Tabella unica per tutte le proprietà (configurazioni):
--   - property_key: chiave
--   - property_value: valore (string)
--   - value_type: tipo logico (casting lato applicazione)
--
-- Scope:
--   - user_id NOT NULL   => proprietà specifica utente
--   - role_id NOT NULL   => proprietà specifica ruolo
--   - is_global = TRUE   => proprietà globale
--
-- Tenant:
--   - tenant = 'realmA'  => valida solo per quel realm
--   - tenant IS NULL     => fallback cross-tenant (opzionale)
-- =====================================================================
CREATE TABLE app_properties (
  id BIGSERIAL PRIMARY KEY,

  -- Tenant/realm (Keycloak). NULL = cross-tenant fallback (opzionale).
  tenant VARCHAR(100),

  -- Scope della proprietà (mutualmente esclusivo)
  user_id BIGINT REFERENCES app_users(id) ON DELETE CASCADE,
  role_id BIGINT REFERENCES app_roles(id) ON DELETE CASCADE,
  is_global BOOLEAN NOT NULL DEFAULT FALSE,

  -- Key/Value
  property_key TEXT NOT NULL,
  property_value TEXT,
  value_type VARCHAR(100) NOT NULL DEFAULT 'java.lang.String',

  -- Documentazione
  description TEXT,

  -- Audit base
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

  -- Coerenza scope
  CONSTRAINT chk_property_scope
    CHECK (
      (user_id IS NOT NULL AND role_id IS NULL AND is_global = FALSE)
      OR
      (user_id IS NULL AND role_id IS NOT NULL AND is_global = FALSE)
      OR
      (user_id IS NULL AND role_id IS NULL AND is_global = TRUE)
    )
);

COMMENT ON TABLE app_properties IS
  'Key/value properties con scope (user/role/global), tenant/realm e precedenza USER > ROLE(priority) > GLOBAL.';

COMMENT ON COLUMN app_properties.tenant IS
  'Tenant/realm (Keycloak). NULL = property cross-tenant (fallback) se abilitato.';

COMMENT ON COLUMN app_properties.property_key IS
  'Chiave della proprietà (dot-notation supportata).';

COMMENT ON COLUMN app_properties.property_value IS
  'Valore della proprietà (memorizzato come stringa).';

COMMENT ON COLUMN app_properties.value_type IS
  'Tipo logico per casting applicativo (es. java.lang.String, java.lang.Boolean).';

COMMENT ON COLUMN app_properties.is_global IS
  'Se TRUE la property è globale (user_id e role_id devono essere NULL).';


-- =====================================================================
-- INDICI (tenant-aware + performance)
-- =====================================================================

-- Lookup per chiave (frequentissimo)
CREATE INDEX idx_properties_key
  ON app_properties(property_key);

-- Lookup per tenant
CREATE INDEX idx_properties_tenant
  ON app_properties(tenant);

-- Lookup rapido per proprietà user-scope
CREATE INDEX idx_properties_user
  ON app_properties(user_id)
  WHERE user_id IS NOT NULL;

-- Lookup rapido per proprietà role-scope
CREATE INDEX idx_properties_role
  ON app_properties(role_id)
  WHERE role_id IS NOT NULL;

-- Unicità per scope includendo tenant.
-- PostgreSQL tratta NULL come "tutti diversi", quindi per garantire unicità con tenant NULL
-- usiamo indici parziali separati.

-- USER scope con tenant valorizzato
CREATE UNIQUE INDEX ux_properties_user_tenant_key
  ON app_properties(user_id, tenant, property_key)
  WHERE user_id IS NOT NULL AND tenant IS NOT NULL;

-- USER scope con tenant NULL (cross-tenant)
CREATE UNIQUE INDEX ux_properties_user_nulltenant_key
  ON app_properties(user_id, property_key)
  WHERE user_id IS NOT NULL AND tenant IS NULL;

-- ROLE scope con tenant valorizzato
CREATE UNIQUE INDEX ux_properties_role_tenant_key
  ON app_properties(role_id, tenant, property_key)
  WHERE role_id IS NOT NULL AND tenant IS NOT NULL;

-- ROLE scope con tenant NULL (cross-tenant)
CREATE UNIQUE INDEX ux_properties_role_nulltenant_key
  ON app_properties(role_id, property_key)
  WHERE role_id IS NOT NULL AND tenant IS NULL;

-- GLOBAL scope con tenant valorizzato
CREATE UNIQUE INDEX ux_properties_global_tenant_key
  ON app_properties(tenant, property_key)
  WHERE is_global = TRUE AND tenant IS NOT NULL;

-- GLOBAL scope con tenant NULL (cross-tenant)
CREATE UNIQUE INDEX ux_properties_global_nulltenant_key
  ON app_properties(property_key)
  WHERE is_global = TRUE AND tenant IS NULL;


-- =====================================================================
-- TRIGGER: updated_at automatico su UPDATE
-- =====================================================================
-- In questo modo chi aggiorna una property non deve ricordarsi di settare updated_at.
-- =====================================================================
CREATE OR REPLACE FUNCTION trg_set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at := now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER app_properties_set_updated_at
BEFORE UPDATE ON app_properties
FOR EACH ROW
EXECUTE FUNCTION trg_set_updated_at();


-- =====================================================================
-- VIEW: effective_properties
-- =====================================================================
-- Restituisce la proprietà "effettiva" per ciascun utente e property_key,
-- risolvendo precedenza e fallback tenant.
--
-- Precedenza scope:
--   1) USER
--   2) ROLE (con role.priority più alta)
--   3) GLOBAL
--
-- Precedenza tenant:
--   - tenant specifico (p.tenant = u.tenant) vince su tenant NULL
--   - tenant NULL è fallback cross-tenant
--
-- Tie-break deterministico:
--   - role.priority DESC
--   - role_id DESC
--   - property.id DESC
--
-- Output:
--   (user_id, tenant, property_key, property_value, value_type)
-- =====================================================================
CREATE VIEW effective_properties AS
SELECT
  user_id,
  tenant,
  property_key,
  property_value,
  value_type
FROM (
  SELECT
    u.id AS user_id,
    u.tenant AS tenant,
    p.property_key,
    p.property_value,
    p.value_type,
    ROW_NUMBER() OVER (
      PARTITION BY u.id, p.property_key
      ORDER BY
        -- scope priority
        (p.user_id IS NOT NULL) DESC,
        (p.role_id IS NOT NULL) DESC,
        (p.is_global = TRUE) DESC,

        -- tenant specificity (tenant match beats tenant NULL)
        (p.tenant IS NOT NULL) DESC,

        -- role precedence among multiple roles
        COALESCE(r.priority, 0) DESC,

        -- deterministic tie-breakers
        p.role_id DESC NULLS LAST,
        p.id DESC
    ) AS rn
  FROM app_users u
  JOIN app_properties p
    ON (
         p.user_id = u.id
      OR p.role_id IN (
           SELECT ur.role_id
           FROM app_user_roles ur
           WHERE ur.user_id = u.id
         )
      OR p.is_global = TRUE
    )
  LEFT JOIN app_roles r
    ON r.id = p.role_id
  WHERE u.enabled = TRUE
    -- tenant match: stesso tenant oppure fallback cross-tenant (NULL)
    AND (p.tenant = u.tenant OR p.tenant IS NULL)
) ranked
WHERE rn = 1;

COMMENT ON VIEW effective_properties IS
  'Proprietà effettive per utente (tenant-aware) con precedenza USER > ROLE(priority) > GLOBAL e fallback tenant NULL.';


-- =====================================================================
-- COME SI USA IN PRATICA (ESEMPI)
-- =====================================================================
-- 1) Tutte le proprietà effettive di un utente:
--    SELECT property_key, property_value, value_type
--    FROM effective_properties
--    WHERE user_id = :userId;
--
-- 2) Singola proprietà risolta:
--    SELECT property_value, value_type
--    FROM effective_properties
--    WHERE user_id = :userId
--      AND property_key = 'documento.{extension.webdesktopBase}repertorio';
--
-- 3) Come funziona il tenant:
--    - per u.tenant='realmA' la view considera:
--        * p.tenant='realmA' (più specifica, prioritaria)
--        * p.tenant IS NULL (fallback cross-tenant)
--
-- 4) Come funziona la precedenza tra ruoli:
--    - se due ruoli definiscono la stessa property_key:
--        vince il ruolo con app_roles.priority più alta.
-- =====================================================================

COMMIT;
