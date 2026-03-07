package com.foundryvtt.bot.spirit.system.core.ws;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.service.RestRelayService;
import com.foundryvtt.bot.spirit.system.core.model.WorldContext;

/**
 * Placeholder websocket gateway for relay integration.
 */
@ApplicationScoped
public class NoopRelayWebSocketGateway implements RelayWebSocketGateway {

    /**
     * Logger for websocket gateway lifecycle.
     */
    private static final Logger LOG = Logger.getLogger(NoopRelayWebSocketGateway.class);

    /**
     * Relay service used to build websocket endpoint URIs.
     */
    private final RestRelayService restRelayService;

    /**
     * In-memory set of client ids currently flagged as connected.
     */
    private final Set<String> connectedClientIds;

    /**
     * Builds the placeholder gateway.
     *
     * @param restRelayService relay service dependency
     */
    @Inject
    public NoopRelayWebSocketGateway(RestRelayService restRelayService) {
        this.restRelayService = restRelayService;
        this.connectedClientIds = Collections.newSetFromMap(
                new ConcurrentHashMap<String, Boolean>());
    }

    @Override
    public URI buildUri(WorldContext worldContext, String token) {
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        String clientId = worldContext.getClientId();
        return this.restRelayService.buildRelayWebSocketUri(clientId, token);
    }

    @Override
    public void connect(WorldContext worldContext, String token) {
        URI uri = this.buildUri(worldContext, token);
        String clientId = worldContext.getClientId();
        LOG.infof(
                "Noop relay websocket connect for client '%s' at '%s'. Replace with real websocket client.",
                clientId,
                uri);
        this.connectedClientIds.add(clientId);
    }

    @Override
    public void disconnect(String clientId) {
        if (clientId == null || clientId.isBlank()) {
            return;
        }
        this.connectedClientIds.remove(clientId);
        LOG.infof("Noop relay websocket disconnected for client '%s'.", clientId);
    }

    @Override
    public void sendJson(String clientId, Map<String, Object> payload) {
        if (!this.isConnected(clientId)) {
            throw new IllegalStateException(
                    "Relay websocket is not connected for client: " + clientId);
        }
        LOG.infof(
                "Noop relay websocket send for client '%s' with payload keys: %s",
                clientId,
                payload == null ? "[]" : payload.keySet());
    }

    @Override
    public boolean isConnected(String clientId) {
        if (clientId == null || clientId.isBlank()) {
            return false;
        }
        return this.connectedClientIds.contains(clientId);
    }
}
