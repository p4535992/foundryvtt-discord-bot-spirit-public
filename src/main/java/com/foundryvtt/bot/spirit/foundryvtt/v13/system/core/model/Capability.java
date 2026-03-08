package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model;

/**
 * Capability flags exposed by a system module.
 */
public enum Capability {

    /**
     * Generic roll endpoints.
     */
    CORE_ROLLS,

    /**
     * Generic search endpoints.
     */
    CORE_SEARCH,

    /**
     * Session-management endpoints.
     */
    CORE_SESSION,

    /**
     * DnD5e-specific endpoints.
     */
    SYSTEM_SPECIFIC_DND5E,

    /**
     * Raw JavaScript execution endpoint.
     */
    RAW_EXECUTE_JS
}
