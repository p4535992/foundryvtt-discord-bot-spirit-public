package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import java.net.URI;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service.FoundryCoreModelService;
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
public class RestRelayServiceImpl extends AbstractRelayClientService implements RestRelayService {

    /**
     * Logger for relay bridge activities.
     */
    private static final Logger LOG = Logger.getLogger(RestRelayServiceImpl.class);

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
    private final FoundryCoreModelService foundryCoreModelService;

    /**
     * Builds the relay bridge service with Quarkus config values.
     *
     * @param relayBaseUrl      relay base URL
     * @param relayApiKey       relay API key
     * @param relayWebSocketUrl relay websocket URL
     */
    @Inject
    public RestRelayServiceImpl(
            FoundryCoreModelService foundryCoreModelService,
            @ConfigProperty(name = "spirit.relay.base-url", defaultValue = "http://localhost:30000") String relayBaseUrl,
            @ConfigProperty(name = "spirit.relay.api-key", defaultValue = "") String relayApiKey,
            @ConfigProperty(name = "spirit.relay.websocket-url", defaultValue = "ws://localhost:30000/relay") String relayWebSocketUrl) {
        super(relayBaseUrl, relayApiKey);
        this.foundryCoreModelService = foundryCoreModelService;
        this.relayWebSocketUrl = relayWebSocketUrl;

        this.relayApiClient = new ApiClient()
                .setBasePath(this.getRelayBaseUrl())
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
    public RelayStatusResult getRelayStatus() {
        try {
            return this.foundryCoreModelService.toRelayStatusResult(this.defaultApi.apiStatusGet());
        } catch (ApiException exception) {
            throw this.relayCallFailed("get relay status", exception);
        }
    }

    @Override
    public RelayConnectedClientsResult getConnectedClients(String apiKeyOverride) {
        try {
            return this.foundryCoreModelService.toConnectedClientsResult(
                    this.defaultApi.clientsGet(this.resolveApiKey(apiKeyOverride)));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get connected clients", exception);
        }
    }

    @Override
    public RelaySessionsResult getCurrentSessions(String apiKeyOverride) {
        try {
            return this.foundryCoreModelService.toSessionsResult(
                    this.sessionApi.sessionGet(this.resolveApiKey(apiKeyOverride)));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get active sessions", exception);
        }
    }

    @Override
    public RelaySessionHandshakeResult createSessionHandshake(
            String apiKeyOverride,
            String foundryUrl,
            String username,
            String password,
            String worldName,
            Map<String, Object> requestBody) {
        try {
            return this.foundryCoreModelService
                    .toSessionHandshakeResult(this.sessionApi.sessionHandshakePost(
                            this.resolveApiKey(apiKeyOverride),
                            foundryUrl,
                            username,
                            password,
                            worldName,
                            requestBody));
        } catch (ApiException exception) {
            throw this.relayCallFailed("create session handshake", exception);
        }
    }

    @Override
    public RelaySessionOperationResult startSession(String apiKeyOverride, Object requestBody) {
        try {
            return this.foundryCoreModelService
                    .toSessionOperationResult(this.sessionApi.startSessionPost(
                            this.resolveApiKey(apiKeyOverride),
                            this.asStringObjectMapOrNull(requestBody)));
        } catch (ApiException exception) {
            throw this.relayCallFailed("start session", exception);
        }
    }

    @Override
    public RelaySessionOperationResult endSession(String apiKeyOverride, String sessionId) {
        try {
            return this.foundryCoreModelService.toSessionOperationResult(
                    this.sessionApi.endSessionDelete(this.resolveApiKey(apiKeyOverride),
                            sessionId));
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
            return this.utilitiesApi.executeJsPost(
                    this.resolveApiKey(apiKeyOverride),
                    clientId,
                    this.asRequiredStringObjectMap(requestBody));
        } catch (ApiException exception) {
            throw this.relayCallFailed("execute javascript", exception);
        }
    }

    @Override
    public URI buildRelayWebSocketUri(String clientId, String token) {
        String tokenValue = token;
        if (!this.hasText(tokenValue)) {
            tokenValue = this.resolveApiKey(null);
        }
        return this.buildRelayWebSocketUri(this.relayWebSocketUrl, clientId, tokenValue);
    }

    @Override
    public String getRelayBaseUrl() {
        return super.getRelayBaseUrl();
    }

    @Override
    public String getRelayWebSocketUrl() {
        return this.relayWebSocketUrl;
    }

    /**
     * Creates a consistent runtime exception for relay client failures.
     *
     * @param actionName relay action description
     * @param exception  root OpenAPI client exception
     * @return runtime exception to throw
     */
    private RuntimeException relayCallFailed(String actionName, ApiException exception) {
        return this.relayCallFailed(LOG, "Relay", actionName, exception.getCode(), exception);
    }
}
