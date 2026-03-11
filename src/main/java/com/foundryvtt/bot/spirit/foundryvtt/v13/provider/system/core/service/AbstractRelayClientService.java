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
 *
 * <p>
 * This class centralizes infrastructure concerns common to both the core and dnd5e relay-backed
 * services: API key resolution, loose JSON-body normalization, websocket URI construction, and
 * uniform downstream error translation to {@link RelayClientException}.
 */
public abstract class AbstractRelayClientService {

    private final String relayBaseUrl;
    private final String relayDefaultApiKey;

    protected AbstractRelayClientService(String relayBaseUrl, String relayDefaultApiKey) {
        this.relayBaseUrl = relayBaseUrl;
        this.relayDefaultApiKey = relayDefaultApiKey;
    }

    /**
     * Returns the configured relay base URL.
     *
     * @return relay base URL
     */
    protected String getRelayBaseUrl() {
        return this.relayBaseUrl;
    }

    /**
     * Resolves the effective API key for a downstream relay call.
     *
     * <p>
     * An explicit override wins; otherwise the configured default is used.
     *
     * @param apiKeyOverride optional per-call override
     * @return effective API key, or {@code null} when none is configured
     */
    protected String resolveApiKey(String apiKeyOverride) {
        if (this.hasText(apiKeyOverride)) {
            return apiKeyOverride;
        }
        if (this.hasText(this.relayDefaultApiKey)) {
            return this.relayDefaultApiKey;
        }
        return null;
    }

    /**
     * Checks whether a string is non-null and non-blank.
     *
     * @param value input string
     * @return {@code true} when the string contains visible text
     */
    protected boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    /**
     * Converts a loose JSON body object to {@code Map<String, Object>}.
     *
     * <p>
     * This is used on provider entrypoints that still accept generic JSON objects and need to feed
     * them into generated relay clients expecting a map payload.
     *
     * @param value candidate JSON object
     * @return converted map, or {@code null} when the input is {@code null}
     */
    protected Map<String, Object> asStringObjectMapOrNull(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map<?, ?>) {
            return this.toStringObjectMap((Map<?, ?>) value);
        }
        throw new IllegalArgumentException("Request body must be a JSON object map.");
    }

    /**
     * Same as {@link #asStringObjectMapOrNull(Object)} but rejects {@code null}.
     *
     * @param value candidate JSON object
     * @return converted map
     */
    protected Map<String, Object> asRequiredStringObjectMap(Object value) {
        Map<String, Object> converted = this.asStringObjectMapOrNull(value);
        if (converted == null) {
            throw new IllegalArgumentException("Request body must not be null.");
        }
        return converted;
    }

    /**
     * Converts an unknown map shape to {@code Map<String, Object>}.
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

    /**
     * Builds the effective relay websocket URI for a specific client/token pair.
     *
     * @param relayWebSocketUrl configured relay websocket base URL
     * @param clientId          relay client id
     * @param token             websocket token or API key
     * @return websocket URI
     */
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

    /**
     * Creates a normalized runtime exception for downstream relay failures.
     *
     * @param logger       logger to use
     * @param serviceLabel human-readable service label
     * @param actionName   failed action description
     * @param statusCode   downstream HTTP status
     * @param exception    original exception
     * @return typed relay client exception
     */
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
