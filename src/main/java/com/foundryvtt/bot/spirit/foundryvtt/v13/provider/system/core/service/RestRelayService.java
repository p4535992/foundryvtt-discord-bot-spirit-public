package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;

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
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;

/**
 * Entry point for Foundry REST relay operations used by the Discord bot layer.
 */
public interface RestRelayService {

    /**
     * Reads relay health/status information.
     *
     * @return relay status payload
     */
    RelayStatusResult getRelayStatus();

    /**
     * Lists currently connected Foundry clients.
     *
     * @param apiKeyOverride optional API key override
     * @return connected clients payload
     */
    RelayConnectedClientsResult getConnectedClients(String apiKeyOverride);

    /**
     * Reads current headless session state.
     *
     * @param apiKeyOverride optional API key override
     * @return current session payload
     */
    RelaySessionsResult getCurrentSessions(String apiKeyOverride);

    /**
     * Starts a session handshake flow.
     *
     * @param apiKeyOverride optional API key override
     * @param foundryUrl     Foundry URL
     * @param username       Foundry username
     * @param password       Foundry password
     * @param worldName      optional world name
     * @param requestBody    optional body data
     * @return handshake payload
     */
    RelaySessionHandshakeResult createSessionHandshake(
            String apiKeyOverride,
            String foundryUrl,
            String username,
            String password,
            String worldName,
            Map<String, Object> requestBody);

    /**
     * Starts a headless Foundry session.
     *
     * @param apiKeyOverride optional API key override
     * @param requestBody    request payload
     * @return start session payload
     */
    RelaySessionOperationResult startSession(String apiKeyOverride, Object requestBody);

    /**
     * Ends a headless Foundry session.
     *
     * @param apiKeyOverride optional API key override
     * @param sessionId      session identifier
     * @return end session payload
     */
    RelaySessionOperationResult endSession(String apiKeyOverride, String sessionId);

    /**
     * Reads the structural snapshot of the connected Foundry world.
     *
     * <p>
     * The returned model is the manual core model, not the generated relay model. Conversion from
     * generated transport payload to manual domain model happens inside the service implementation.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @return typed structure payload
     */
    RelayStructureResult getStructure(String apiKeyOverride, String clientId);

    /**
     * Reads the current encounter state for a connected Foundry client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @return typed encounter payload
     */
    RelayEncounterResult getEncounters(String apiKeyOverride, String clientId);

    /**
     * Reads the currently selected entity or actor-scoped entity.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param selected       when {@code true}, uses current selection
     * @param actor          when {@code true}, resolves actor context
     * @return typed entity payload
     */
    RelayEntityResult getEntity(String apiKeyOverride, String clientId, Boolean selected,
            Boolean actor);

    /**
     * Reads a specific entity by Foundry UUID.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param uuid           Foundry UUID
     * @param actor          when {@code true}, resolves actor context
     * @return typed entity payload
     */
    RelayEntityResult getEntityByUuid(String apiKeyOverride, String clientId, String uuid,
            Boolean actor);

    /**
     * Reads a rendered actor sheet from the relay and normalizes it to JSON.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param uuid           optional actor UUID
     * @param selected       when {@code true}, uses current selection
     * @param actor          when {@code true}, resolves actor context
     * @param scale          optional render scale
     * @return typed actor sheet payload
     */
    RelayActorSheetResult getActorSheet(String apiKeyOverride, String clientId, String uuid,
            Boolean selected, Boolean actor, BigDecimal scale);

    /**
     * Executes a dice roll for a specific connected client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param rollRequest    roll request payload
     * @return roll payload
     */
    RelayRollResult roll(String apiKeyOverride, String clientId, RollRequest rollRequest);

    /**
     * Reads the latest roll for a target client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @return last roll payload
     */
    RelayLastRollResult getLastRoll(String apiKeyOverride, String clientId);

    /**
     * Reads recent roll history for a target client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param limit          max records
     * @return rolls payload
     */
    RelayRollsResult getRecentRolls(String apiKeyOverride, String clientId, Integer limit);

    /**
     * Searches Foundry entities through relay.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param query          query string
     * @param filter         optional filter expression
     * @return search payload
     */
    RelaySearchResult search(String apiKeyOverride, String clientId, String query, String filter);

    /**
     * Executes raw JavaScript in the target Foundry world through relay.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param requestBody    request payload (for example {"script":"..."})
     * @return execution payload
     */
    RelayExecuteJavaScriptResult executeJavaScript(String apiKeyOverride, String clientId,
            Object requestBody);

    /**
     * Builds a relay websocket URI based on configured base websocket endpoint.
     *
     * @param clientId target client id
     * @param token    websocket token; if blank, the configured API key is used
     * @return websocket URI
     */
    URI buildRelayWebSocketUri(String clientId, String token);

    /**
     * Reads configured relay base URL.
     *
     * @return base URL
     */
    String getRelayBaseUrl();

    /**
     * Reads configured relay websocket URL.
     *
     * @return websocket URL
     */
    String getRelayWebSocketUrl();
}
