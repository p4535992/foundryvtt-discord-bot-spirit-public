package com.foundryvtt.bot.spirit.service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.DefaultApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.RollApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.SearchApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.SessionApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.UtilitiesApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.invoker.ApiClient;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.invoker.ApiException;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;

/**
 * Default bridge service between Quarkus/JDA code and the generated relay OpenAPI client.
 */
@ApplicationScoped
public class RestRelayServiceImpl implements RestRelayService {

    /**
     * Logger for relay bridge activities.
     */
    private static final Logger LOG = Logger.getLogger(RestRelayServiceImpl.class);

    /**
     * Configured relay HTTP base URL.
     */
    private final String relayBaseUrl;

    /**
     * Configured relay API key.
     */
    private final String relayApiKey;

    /**
     * Configured relay websocket URL.
     */
    private final String relayWebSocketUrl;

    /**
     * Shared generated OpenAPI client.
     */
    private final ApiClient relayApiClient;

    /**
     * Generated API facade for generic endpoints.
     */
    private final DefaultApi defaultApi;

    /**
     * Generated API facade for session endpoints.
     */
    private final SessionApi sessionApi;

    /**
     * Generated API facade for roll endpoints.
     */
    private final RollApi rollApi;

    /**
     * Generated API facade for search endpoints.
     */
    private final SearchApi searchApi;

    /**
     * Generated API facade for utility endpoints.
     */
    private final UtilitiesApi utilitiesApi;

    /**
     * Builds the relay bridge service with Quarkus config values.
     *
     * @param relayBaseUrl      relay base URL
     * @param relayApiKey       relay API key
     * @param relayWebSocketUrl relay websocket URL
     */
    @Inject
    public RestRelayServiceImpl(
            @ConfigProperty(name = "spirit.relay.base-url", defaultValue = "http://localhost:30000") String relayBaseUrl,
            @ConfigProperty(name = "spirit.relay.api-key", defaultValue = "") String relayApiKey,
            @ConfigProperty(name = "spirit.relay.websocket-url", defaultValue = "ws://localhost:30000/relay") String relayWebSocketUrl) {
        this.relayBaseUrl = relayBaseUrl;
        this.relayApiKey = relayApiKey;
        this.relayWebSocketUrl = relayWebSocketUrl;

        this.relayApiClient = new ApiClient()
                .setBasePath(relayBaseUrl)
                .setConnectTimeout(10_000)
                .setReadTimeout(30_000)
                .setWriteTimeout(30_000);

        this.defaultApi = new DefaultApi(this.relayApiClient);
        this.sessionApi = new SessionApi(this.relayApiClient);
        this.rollApi = new RollApi(this.relayApiClient);
        this.searchApi = new SearchApi(this.relayApiClient);
        this.utilitiesApi = new UtilitiesApi(this.relayApiClient);
    }

    @Override
    public Object getRelayStatus() {
        try {
            return this.defaultApi.apiStatusGet();
        } catch (ApiException exception) {
            throw this.relayCallFailed("get relay status", exception);
        }
    }

    @Override
    public Object getConnectedClients(String apiKeyOverride) {
        try {
            return this.defaultApi.clientsGet(this.resolveApiKey(apiKeyOverride));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get connected clients", exception);
        }
    }

    @Override
    public Object getCurrentSessions(String apiKeyOverride) {
        try {
            return this.sessionApi.sessionGet(this.resolveApiKey(apiKeyOverride));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get active sessions", exception);
        }
    }

    @Override
    public Object createSessionHandshake(
            String apiKeyOverride,
            String foundryUrl,
            String username,
            String password,
            String worldName,
            Map<String, Object> requestBody) {
        try {
            return this.sessionApi.sessionHandshakePost(
                    this.resolveApiKey(apiKeyOverride),
                    foundryUrl,
                    username,
                    password,
                    worldName,
                    requestBody);
        } catch (ApiException exception) {
            throw this.relayCallFailed("create session handshake", exception);
        }
    }

    @Override
    public Object startSession(String apiKeyOverride, Object requestBody) {
        try {
            return this.sessionApi.startSessionPost(this.resolveApiKey(apiKeyOverride),
                    this.asStringObjectMapOrNull(requestBody));
        } catch (ApiException exception) {
            throw this.relayCallFailed("start session", exception);
        }
    }

    @Override
    public Object endSession(String apiKeyOverride, String sessionId) {
        try {
            return this.sessionApi.endSessionDelete(this.resolveApiKey(apiKeyOverride), sessionId);
        } catch (ApiException exception) {
            throw this.relayCallFailed("end session", exception);
        }
    }

    @Override
    public Object roll(String apiKeyOverride, String clientId, RollRequest rollRequest) {
        try {
            return this.rollApi.rollPost(this.resolveApiKey(apiKeyOverride), clientId, rollRequest);
        } catch (ApiException exception) {
            throw this.relayCallFailed("execute roll", exception);
        }
    }

    @Override
    public Object getLastRoll(String apiKeyOverride, String clientId) {
        try {
            return this.rollApi.lastrollGet(this.resolveApiKey(apiKeyOverride), clientId);
        } catch (ApiException exception) {
            throw this.relayCallFailed("get last roll", exception);
        }
    }

    @Override
    public Object getRecentRolls(String apiKeyOverride, String clientId, Integer limit) {
        try {
            return this.rollApi.rollsGet(this.resolveApiKey(apiKeyOverride), clientId, limit);
        } catch (ApiException exception) {
            throw this.relayCallFailed("get roll history", exception);
        }
    }

    @Override
    public Object search(String apiKeyOverride, String clientId, String query, String filter) {
        try {
            return this.searchApi.searchGet(this.resolveApiKey(apiKeyOverride), clientId, query,
                    filter);
        } catch (ApiException exception) {
            throw this.relayCallFailed("search entities", exception);
        }
    }

    @Override
    public Object executeJavaScript(String apiKeyOverride, String clientId, Object requestBody) {
        try {
            return this.utilitiesApi.executeJsPost(this.resolveApiKey(apiKeyOverride), clientId,
                    this.asRequiredStringObjectMap(requestBody));
        } catch (ApiException exception) {
            throw this.relayCallFailed("execute javascript", exception);
        }
    }

    @Override
    public URI buildRelayWebSocketUri(String clientId, String token) {
        if (!this.hasText(clientId)) {
            return URI.create(this.relayWebSocketUrl);
        }

        String tokenValue = token;
        if (!this.hasText(tokenValue)) {
            tokenValue = this.resolveApiKey(null);
        }

        if (!this.hasText(tokenValue)) {
            return URI.create(this.relayWebSocketUrl);
        }

        String encodedClientId = URLEncoder.encode(clientId, StandardCharsets.UTF_8);
        String encodedToken = URLEncoder.encode(tokenValue, StandardCharsets.UTF_8);
        String separator = this.relayWebSocketUrl.contains("?") ? "&" : "?";
        String fullWebSocketUrl = this.relayWebSocketUrl
                + separator
                + "id="
                + encodedClientId
                + "&token="
                + encodedToken;
        return URI.create(fullWebSocketUrl);
    }

    @Override
    public String getRelayBaseUrl() {
        return this.relayBaseUrl;
    }

    @Override
    public String getRelayWebSocketUrl() {
        return this.relayWebSocketUrl;
    }

    /**
     * Resolves API key by preferring method override over configured key.
     *
     * @param apiKeyOverride runtime API key override
     * @return effective API key, or {@code null} when both are empty
     */
    private String resolveApiKey(String apiKeyOverride) {
        if (this.hasText(apiKeyOverride)) {
            return apiKeyOverride;
        }
        if (this.hasText(this.relayApiKey)) {
            return this.relayApiKey;
        }
        return null;
    }

    /**
     * Checks if a value contains at least one non-whitespace character.
     *
     * @param value input value
     * @return {@code true} when value has text
     */
    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    /**
     * Converts an object to a {@code Map<String, Object>} when possible.
     *
     * @param value source value
     * @return converted map, or {@code null} when value is null
     */
    private Map<String, Object> asStringObjectMapOrNull(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map<?, ?>) {
            return this.toStringObjectMap((Map<?, ?>) value);
        }
        throw new IllegalArgumentException("Request body must be a JSON object map.");
    }

    /**
     * Converts an object to a required {@code Map<String, Object>}.
     *
     * @param value source value
     * @return converted map
     */
    private Map<String, Object> asRequiredStringObjectMap(Object value) {
        Map<String, Object> converted = this.asStringObjectMapOrNull(value);
        if (converted == null) {
            throw new IllegalArgumentException("Request body must not be null.");
        }
        return converted;
    }

    /**
     * Converts an unknown map to {@code Map<String, Object>}.
     *
     * @param rawMap source map
     * @return converted map
     */
    private Map<String, Object> toStringObjectMap(Map<?, ?> rawMap) {
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
     * Creates a consistent runtime exception for relay client failures.
     *
     * @param actionName relay action description
     * @param exception  root OpenAPI client exception
     * @return runtime exception to throw
     */
    private RuntimeException relayCallFailed(String actionName, ApiException exception) {
        int statusCode = exception.getCode();
        LOG.errorf(
                exception,
                "Relay call failed for action '%s' (status=%d, baseUrl=%s).",
                actionName,
                statusCode,
                this.relayBaseUrl);
        return new IllegalStateException(
                "Relay call failed while trying to " + actionName + " (status=" + statusCode + ").",
                exception);
    }
}
