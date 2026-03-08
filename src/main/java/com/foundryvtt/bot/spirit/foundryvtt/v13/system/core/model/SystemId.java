package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model;

import java.util.Locale;

/**
 * Supported Foundry game-system identifiers.
 */
public enum SystemId {

    /**
     * Dungeons and Dragons Fifth Edition system id.
     */
    DND5E("dnd5e"),

    /**
     * Unknown or unsupported system id.
     */
    UNKNOWN("unknown");

    /**
     * Raw system identifier value.
     */
    private final String value;

    /**
     * Creates an enum constant with the configured value.
     *
     * @param value raw system id string
     */
    SystemId(String value) {
        this.value = value;
    }

    /**
     * Returns the raw system identifier value.
     *
     * @return raw system id string
     */
    public String value() {
        return this.value;
    }

    /**
     * Resolves a {@link SystemId} from a raw Foundry system id string.
     *
     * @param value raw system id string
     * @return matching enum value, or {@link #UNKNOWN} when no match exists
     */
    public static SystemId fromValue(String value) {
        if (value == null || value.isBlank()) {
            return UNKNOWN;
        }
        String normalized = value.trim().toLowerCase(Locale.ROOT);
        for (SystemId item : values()) {
            if (item.value.equals(normalized)) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
