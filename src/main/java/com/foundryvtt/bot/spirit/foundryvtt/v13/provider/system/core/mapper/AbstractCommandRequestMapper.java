package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Shared request-payload conversion helpers for provider command mappers.
 */
public abstract class AbstractCommandRequestMapper {

    private final ObjectMapper objectMapper;

    protected AbstractCommandRequestMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected ObjectMapper objectMapper() {
        return this.objectMapper;
    }

    protected Map<String, Object> safePayload(Map<String, Object> payload) {
        if (payload == null) {
            return new HashMap<String, Object>();
        }
        return payload;
    }

    protected String requireClientId(String clientId, Map<String, Object> payload) {
        String localClientId = clientId;
        if (localClientId == null || localClientId.isBlank()) {
            localClientId = this.readOptionalString(payload, "clientId");
        }
        if (localClientId == null || localClientId.isBlank()) {
            throw new IllegalArgumentException("clientId must not be blank");
        }
        return localClientId;
    }

    protected String readRequiredString(Map<String, Object> payload, String fieldName) {
        String value = this.readOptionalString(payload, fieldName);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        return value;
    }

    protected String readOptionalString(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        return String.valueOf(rawValue);
    }

    protected Boolean readOptionalBoolean(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof Boolean booleanValue) {
            return booleanValue;
        }
        return Boolean.valueOf(String.valueOf(rawValue));
    }

    protected Integer readOptionalInteger(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof Number numberValue) {
            return Integer.valueOf(numberValue.intValue());
        }
        String rawText = String.valueOf(rawValue).trim();
        if (rawText.isEmpty()) {
            return null;
        }
        return Integer.valueOf(rawText);
    }

    protected Integer readRequiredInteger(Map<String, Object> payload, String fieldName) {
        Integer value = this.readOptionalInteger(payload, fieldName);
        if (value == null) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        return value;
    }

    protected BigDecimal readOptionalBigDecimal(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof BigDecimal bigDecimalValue) {
            return bigDecimalValue;
        }
        if (rawValue instanceof Number numberValue) {
            return BigDecimal.valueOf(numberValue.doubleValue());
        }
        String rawText = String.valueOf(rawValue).trim();
        if (rawText.isEmpty()) {
            return null;
        }
        return new BigDecimal(rawText);
    }

    protected Map<String, Object> readOptionalMap(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof Map<?, ?> mapValue) {
            return this.toStringObjectMap(mapValue);
        }
        throw new IllegalArgumentException("Payload field '" + fieldName + "' must be an object");
    }

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
