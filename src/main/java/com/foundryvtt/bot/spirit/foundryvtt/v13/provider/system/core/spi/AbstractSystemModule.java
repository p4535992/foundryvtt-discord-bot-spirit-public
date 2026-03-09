package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi;

import java.util.HashMap;
import java.util.Map;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;

/**
 * Shared base implementation for system modules with common world-context and payload helpers.
 */
public abstract class AbstractSystemModule implements SystemModule {

    /**
     * Validates that the world context is present and matches the current module system id.
     *
     * @param worldContext target context
     */
    protected void validateWorldContext(WorldContext worldContext) {
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        SystemId expectedSystemId = this.systemId();
        if (worldContext.getSystemId() != expectedSystemId) {
            throw new IllegalStateException(
                    this.getClass().getSimpleName()
                            + " cannot execute commands for system: "
                            + worldContext.getSystemId().value()
                            + " (expected "
                            + expectedSystemId.value()
                            + ")");
        }
    }

    /**
     * Reads a required string from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return non-blank string value
     */
    protected String readRequiredString(Map<String, Object> payload, String fieldName) {
        String value = this.readOptionalString(payload, fieldName);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        return value;
    }

    /**
     * Reads an optional string from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return optional string value
     */
    protected String readOptionalString(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        return String.valueOf(rawValue);
    }

    /**
     * Reads a required integer from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return parsed integer value
     */
    protected Integer readRequiredInteger(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        if (rawValue instanceof Number) {
            Number numberValue = (Number) rawValue;
            return Integer.valueOf(numberValue.intValue());
        }
        String rawText = String.valueOf(rawValue).trim();
        if (rawText.isEmpty()) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        try {
            return Integer.valueOf(rawText);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Invalid integer payload field '" + fieldName + "': " + rawText,
                    exception);
        }
    }

    /**
     * Reads an optional nested map payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return optional nested map
     */
    protected Map<String, Object> readOptionalMap(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof Map<?, ?>) {
            return this.toStringObjectMap((Map<?, ?>) rawValue);
        }
        throw new IllegalArgumentException("Payload field '" + fieldName + "' must be an object");
    }

    /**
     * Converts an unknown map to {@code Map<String, Object>}.
     *
     * @param rawMap source map
     * @return converted map
     */
    protected Map<String, Object> toStringObjectMap(Map<?, ?> rawMap) {
        Map<String, Object> converted = new HashMap<String, Object>();
        for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            converted.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return converted;
    }
}
