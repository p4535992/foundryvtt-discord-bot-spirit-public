package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Shared mapper utilities for hand-written Foundry model conversion services.
 */
public abstract class AbstractFoundryModelService {

    private final ObjectMapper objectMapper;

    protected AbstractFoundryModelService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected <T> T convert(Object payload, Class<T> targetType) {
        if (payload == null) {
            return null;
        }
        if (targetType.isInstance(payload)) {
            return targetType.cast(payload);
        }
        return this.objectMapper.convertValue(payload, targetType);
    }

    protected String normalizeType(String value) {
        return value
                .replace("-", "")
                .replace("_", "")
                .replace(" ", "")
                .toLowerCase(Locale.ROOT);
    }
}
