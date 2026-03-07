package com.foundryvtt.bot.spirit.system.core.ws;

import java.net.URI;
import java.util.Map;

import com.foundryvtt.bot.spirit.system.core.model.WorldContext;

/**
 * Abstraction for websocket interactions with the Foundry relay.
 */
public interface RelayWebSocketGateway {

    /**
     * Builds a websocket URI for a world context.
     *
     * @param worldContext world context
     * @param token        websocket token override
     * @return websocket URI
     */
    URI buildUri(WorldContext worldContext, String token);

    /**
     * Connects to relay websocket for the target context.
     *
     * @param worldContext world context
     * @param token        websocket token override
     */
    void connect(WorldContext worldContext, String token);

    /**
     * Disconnects websocket session for a client id.
     *
     * @param clientId relay client id
     */
    void disconnect(String clientId);

    /**
     * Sends a JSON payload to a connected relay websocket session.
     *
     * @param clientId relay client id
     * @param payload  JSON-like payload
     */
    void sendJson(String clientId, Map<String, Object> payload);

    /**
     * Checks whether a client is marked as connected.
     *
     * @param clientId relay client id
     * @return {@code true} when client is connected
     */
    boolean isConnected(String clientId);
}
