package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.mapper.FoundryCoreMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayActorSheetResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEncounterResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEntityResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayExecuteJavaScriptResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayLastRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySearchResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStructureResult;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.DefaultApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.EncounterApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.EntityApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.RollApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.SearchApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.SessionApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.SheetApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.StructureApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.api.UtilitiesApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.invoker.ApiClient;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.invoker.ApiException;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.invoker.Pair;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;
import com.google.gson.reflect.TypeToken;

/**
 * Default bridge service between the provider layer and the generated relay OpenAPI client.
 *
 * <p>
 * This class is where the separation between transport and domain is kept explicit: provider
 * service call -> generated relay client call -> raw/generated relay payload ->
 * {@link FoundryCoreMapper} -> manual provider-facing core model.
 *
 * <p>
 * The rest of the application should depend on {@link RestRelayService}, not on the generated
 * OpenAPI packages.
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
    private final EntityApi entityApi;
    private final StructureApi structureApi;
    private final EncounterApi encounterApi;
    private final SheetApi sheetApi;

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
    private final FoundryCoreMapper foundryCoreMapper;

    /**
     * Builds the relay bridge service with Quarkus config values.
     *
     * @param relayBaseUrl      relay base URL
     * @param relayApiKey       relay API key
     * @param relayWebSocketUrl relay websocket URL
     */
    @Inject
    public RestRelayServiceImpl(
            FoundryCoreMapper foundryCoreMapper,
            @ConfigProperty(name = "spirit.relay.base-url", defaultValue = "http://localhost:30000") String relayBaseUrl,
            @ConfigProperty(name = "spirit.relay.api-key", defaultValue = "") String relayApiKey,
            @ConfigProperty(name = "spirit.relay.websocket-url", defaultValue = "ws://localhost:30000/relay") String relayWebSocketUrl) {
        super(relayBaseUrl, relayApiKey);
        this.foundryCoreMapper = foundryCoreMapper;
        this.relayWebSocketUrl = relayWebSocketUrl;

        this.relayApiClient = new ApiClient()
                .setBasePath(this.getRelayBaseUrl())
                .setConnectTimeout(10_000)
                .setReadTimeout(30_000)
                .setWriteTimeout(30_000);

        this.defaultApi = new DefaultApi(this.relayApiClient);
        this.sessionApi = new SessionApi(this.relayApiClient);
        this.entityApi = new EntityApi(this.relayApiClient);
        this.structureApi = new StructureApi(this.relayApiClient);
        this.encounterApi = new EncounterApi(this.relayApiClient);
        this.sheetApi = new SheetApi(this.relayApiClient);
        this.rollApi = new RollApi(this.relayApiClient);
        this.searchApi = new SearchApi(this.relayApiClient);
        this.utilitiesApi = new UtilitiesApi(this.relayApiClient);
    }

    @Override
    public RelayStatusResult getRelayStatus() {
        try {
            return this.foundryCoreMapper.toRelayStatusResult(this.defaultApi.apiStatusGet());
        } catch (ApiException exception) {
            throw this.relayCallFailed("get relay status", exception);
        }
    }

    @Override
    public RelayConnectedClientsResult getConnectedClients(String apiKeyOverride) {
        try {
            return this.foundryCoreMapper.toConnectedClientsResult(
                    this.defaultApi.clientsGet(this.resolveApiKey(apiKeyOverride)));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get connected clients", exception);
        }
    }

    @Override
    public RelaySessionsResult getCurrentSessions(String apiKeyOverride) {
        try {
            return this.foundryCoreMapper.toSessionsResult(
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
            return this.foundryCoreMapper
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
            return this.foundryCoreMapper
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
            return this.foundryCoreMapper.toSessionOperationResult(
                    this.sessionApi.endSessionDelete(this.resolveApiKey(apiKeyOverride),
                            sessionId));
        } catch (ApiException exception) {
            throw this.relayCallFailed("end session", exception);
        }
    }

    @Override
    public RelayStructureResult getStructure(String apiKeyOverride, String clientId) {
        try {
            return this.foundryCoreMapper.toStructureResult(
                    this.structureApi.structureGet(this.resolveApiKey(apiKeyOverride), clientId));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get structure", exception);
        }
    }

    @Override
    public RelayEncounterResult getEncounters(String apiKeyOverride, String clientId) {
        try {
            return this.foundryCoreMapper.toEncounterResult(
                    this.encounterApi.encountersGet(this.resolveApiKey(apiKeyOverride), clientId));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get encounters", exception);
        }
    }

    @Override
    public RelayEntityResult getEntity(String apiKeyOverride, String clientId, Boolean selected,
            Boolean actor) {
        try {
            return this.foundryCoreMapper.toEntityResult(
                    this.entityApi.getGet(this.resolveApiKey(apiKeyOverride), clientId, selected,
                            actor));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get entity", exception);
        }
    }

    @Override
    public RelayEntityResult getEntityByUuid(String apiKeyOverride, String clientId, String uuid,
            Boolean actor) {
        try {
            return this.foundryCoreMapper.toEntityResult(
                    this.executeRelayObject(this.buildRelayGetCall(
                            "/get",
                            this.resolveApiKey(apiKeyOverride),
                            this.queryPairs(
                                    "clientId", clientId,
                                    "uuid", uuid,
                                    "actor", actor))));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get entity by uuid", exception);
        }
    }

    @Override
    public RelayActorSheetResult getActorSheet(String apiKeyOverride, String clientId, String uuid,
            Boolean selected, Boolean actor, BigDecimal scale) {
        try {
            return this.foundryCoreMapper.toActorSheetResult(
                    this.executeRelayObject(this.buildRelayGetCall(
                            "/sheet",
                            this.resolveApiKey(apiKeyOverride),
                            this.queryPairs(
                                    "clientId", clientId,
                                    "uuid", uuid,
                                    "selected", selected,
                                    "actor", actor,
                                    "scale", scale,
                                    "format", "json"))));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get actor sheet", exception);
        }
    }

    @Override
    public RelayRollResult roll(String apiKeyOverride, String clientId, RollRequest rollRequest) {
        try {
            return this.foundryCoreMapper.toRollResult(
                    this.rollApi.rollPost(this.resolveApiKey(apiKeyOverride), clientId,
                            rollRequest));
        } catch (ApiException exception) {
            throw this.relayCallFailed("execute roll", exception);
        }
    }

    @Override
    public RelayLastRollResult getLastRoll(String apiKeyOverride, String clientId) {
        try {
            return this.foundryCoreMapper.toLastRollResult(
                    this.rollApi.lastrollGet(this.resolveApiKey(apiKeyOverride), clientId));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get last roll", exception);
        }
    }

    @Override
    public RelayRollsResult getRecentRolls(String apiKeyOverride, String clientId, Integer limit) {
        try {
            return this.foundryCoreMapper.toRollsResult(
                    this.rollApi.rollsGet(this.resolveApiKey(apiKeyOverride), clientId, limit));
        } catch (ApiException exception) {
            throw this.relayCallFailed("get roll history", exception);
        }
    }

    @Override
    public RelaySearchResult search(String apiKeyOverride, String clientId, String query,
            String filter) {
        try {
            return this.foundryCoreMapper.toSearchResult(
                    this.searchApi.searchGet(this.resolveApiKey(apiKeyOverride), clientId, query,
                            filter));
        } catch (ApiException exception) {
            throw this.relayCallFailed("search entities", exception);
        }
    }

    @Override
    public RelayExecuteJavaScriptResult executeJavaScript(String apiKeyOverride, String clientId,
            Object requestBody) {
        try {
            return this.foundryCoreMapper.toExecuteJavaScriptResult(
                    this.utilitiesApi.executeJsPost(
                            this.resolveApiKey(apiKeyOverride),
                            clientId,
                            this.asRequiredStringObjectMap(requestBody)));
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

    /**
     * Executes a manual relay call when the generated OpenAPI client does not expose the exact
     * endpoint signature needed by the provider layer.
     *
     * <p>
     * This is currently used for endpoints such as {@code getByUuid} and JSON-rendered actor
     * sheets, where the relay supports additional query combinations not modeled cleanly by the
     * generated client.
     *
     * @param call prepared OkHttp relay call
     * @return raw payload returned by the relay
     * @throws ApiException when the relay client fails
     */
    private Object executeRelayObject(okhttp3.Call call) throws ApiException {
        Type responseType = new TypeToken<Object>() {
        }.getType();
        return this.relayApiClient.execute(call, responseType).getData();
    }

    /**
     * Builds a manual GET call against the relay using the shared generated client.
     *
     * @param path        relay path
     * @param apiKey      resolved API key
     * @param queryParams query string pairs
     * @return prepared OkHttp call
     * @throws ApiException when call construction fails
     */
    private okhttp3.Call buildRelayGetCall(String path, String apiKey, List<Pair> queryParams)
            throws ApiException {
        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("Accept", "application/json");
        headerParams.put("x-api-key", apiKey);
        return this.relayApiClient.buildCall(
                this.relayApiClient.getBasePath(),
                path,
                "GET",
                queryParams,
                new ArrayList<Pair>(),
                null,
                headerParams,
                new HashMap<String, String>(),
                new HashMap<String, Object>(),
                new String[] {},
                null);
    }

    /**
     * Utility to build query parameter pairs while skipping {@code null} values.
     *
     * <p>
     * This keeps manual relay calls aligned with the generated client behavior and avoids sending
     * blank optional query parameters.
     *
     * @param keyValues alternating key/value list
     * @return query parameter list
     */
    private List<Pair> queryPairs(Object... keyValues) {
        List<Pair> pairs = new ArrayList<Pair>();
        for (int index = 0; index < keyValues.length; index += 2) {
            String name = (String) keyValues[index];
            Object value = keyValues[index + 1];
            if (value != null) {
                pairs.addAll(this.relayApiClient.parameterToPair(name, value));
            }
        }
        return pairs;
    }
}
