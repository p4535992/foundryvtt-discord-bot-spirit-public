package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.exception.RelayClientException;

/**
 * Shared base class for services backed by generated relay OpenAPI clients.
 */
public abstract class AbstractRelayClientService {

    private final String relayBaseUrl;
    private final String relayDefaultApiKey;

    protected AbstractRelayClientService(String relayBaseUrl, String relayDefaultApiKey) {
        this.relayBaseUrl = relayBaseUrl;
        this.relayDefaultApiKey = relayDefaultApiKey;
    }

    protected String getRelayBaseUrl() {
        return this.relayBaseUrl;
    }

    protected String resolveApiKey(String apiKeyOverride) {
        if (this.hasText(apiKeyOverride)) {
            return apiKeyOverride;
        }
        if (this.hasText(this.relayDefaultApiKey)) {
            return this.relayDefaultApiKey;
        }
        return null;
    }

    protected boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    protected Map<String, Object> asStringObjectMapOrNull(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map<?, ?>) {
            return this.toStringObjectMap((Map<?, ?>) value);
        }
        throw new IllegalArgumentException("Request body must be a JSON object map.");
    }

    protected Map<String, Object> asRequiredStringObjectMap(Object value) {
        Map<String, Object> converted = this.asStringObjectMapOrNull(value);
        if (converted == null) {
            throw new IllegalArgumentException("Request body must not be null.");
        }
        return converted;
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

    protected URI buildRelayWebSocketUri(String relayWebSocketUrl, String clientId, String token) {
        if (!this.hasText(clientId)) {
            return URI.create(relayWebSocketUrl);
        }
        if (!this.hasText(token)) {
            return URI.create(relayWebSocketUrl);
        }

        String encodedClientId = URLEncoder.encode(clientId, StandardCharsets.UTF_8);
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
        String separator = relayWebSocketUrl.contains("?") ? "&" : "?";
        String fullWebSocketUrl = relayWebSocketUrl
                + separator
                + "id="
                + encodedClientId
                + "&token="
                + encodedToken;
        return URI.create(fullWebSocketUrl);
    }

    protected RelayClientException relayCallFailed(
            Logger logger,
            String serviceLabel,
            String actionName,
            int statusCode,
            Exception exception) {
        logger.errorf(
                exception,
                "%s call failed for action '%s' (status=%d, baseUrl=%s).",
                serviceLabel,
                actionName,
                statusCode,
                this.relayBaseUrl);
        return new RelayClientException(
                serviceLabel,
                actionName,
                statusCode,
                serviceLabel
                        + " call failed while trying to "
                        + actionName
                        + " (status="
                        + statusCode
                        + ").",
                exception);
    }
}
