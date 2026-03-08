package com.foundryvtt.bot.spirit.service;

import java.net.URI;
import java.util.Map;

import com.foundryvtt.bot.spirit.openapi.relay.system.model.RollRequest;

/**
 * Entry point for Foundry REST relay operations used by the Discord bot layer.
 */
public interface RestRelayService {

    /**
     * Reads relay health/status information.
     *
     * @return relay status payload
     */
    Object getRelayStatus();

    /**
     * Lists currently connected Foundry clients.
     *
     * @param apiKeyOverride optional API key override
     * @return connected clients payload
     */
    Object getConnectedClients(String apiKeyOverride);

    /**
     * Reads current headless session state.
     *
     * @param apiKeyOverride optional API key override
     * @return current session payload
     */
    Object getCurrentSessions(String apiKeyOverride);

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
    Object createSessionHandshake(
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
    Object startSession(String apiKeyOverride, Object requestBody);

    /**
     * Ends a headless Foundry session.
     *
     * @param apiKeyOverride optional API key override
     * @param sessionId      session identifier
     * @return end session payload
     */
    Object endSession(String apiKeyOverride, String sessionId);

    /**
     * Executes a dice roll for a specific connected client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param rollRequest    roll request payload
     * @return roll payload
     */
    Object roll(String apiKeyOverride, String clientId, RollRequest rollRequest);

    /**
     * Reads the latest roll for a target client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @return last roll payload
     */
    Object getLastRoll(String apiKeyOverride, String clientId);

    /**
     * Reads recent roll history for a target client.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param limit          max records
     * @return rolls payload
     */
    Object getRecentRolls(String apiKeyOverride, String clientId, Integer limit);

    /**
     * Searches Foundry entities through relay.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param query          query string
     * @param filter         optional filter expression
     * @return search payload
     */
    Object search(String apiKeyOverride, String clientId, String query, String filter);

    /**
     * Executes raw JavaScript in the target Foundry world through relay.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       target client id
     * @param requestBody    request payload (for example {"script":"..."})
     * @return execution payload
     */
    Object executeJavaScript(String apiKeyOverride, String clientId, Object requestBody);

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
