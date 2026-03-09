package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.RestRelayService;

/**
 * Resolves the Foundry system id associated with a relay client.
 */
@ApplicationScoped
public class FoundrySystemResolverService {

    /**
     * Relay service used to query connected clients.
     */
    private final RestRelayService restRelayService;

    /**
     * Builds the resolver service.
     *
     * @param restRelayService relay service dependency
     */
    @Inject
    public FoundrySystemResolverService(RestRelayService restRelayService) {
        this.restRelayService = restRelayService;
    }

    /**
     * Resolves system id for a target relay client.
     *
     * @param clientId       relay client id
     * @param apiKeyOverride optional API key override
     * @return resolved system id, or {@link SystemId#UNKNOWN}
     */
    public SystemId resolveSystemIdForClient(String clientId, String apiKeyOverride) {
        if (clientId == null || clientId.isBlank()) {
            return SystemId.UNKNOWN;
        }

        Object clientsPayload = this.restRelayService.getConnectedClients(apiKeyOverride);
        List<Map<String, Object>> clients = this.extractClients(clientsPayload);
        for (Map<String, Object> client : clients) {
            String currentClientId = this.asString(client.get("id"));
            if (!clientId.equals(currentClientId)) {
                continue;
            }
            String rawSystemId = this.readSystemId(client);
            return SystemId.fromValue(rawSystemId);
        }
        return SystemId.UNKNOWN;
    }

    /**
     * Extracts a normalized list of client maps from relay payload.
     *
     * @param payload relay payload
     * @return list of client maps
     */
    private List<Map<String, Object>> extractClients(Object payload) {
        List<Map<String, Object>> clients = new ArrayList<Map<String, Object>>();
        if (payload instanceof Map<?, ?> payloadMap) {
            Object clientsNode = payloadMap.get("clients");
            this.addClientMaps(clients, clientsNode);
            return clients;
        }
        this.addClientMaps(clients, payload);
        return clients;
    }

    /**
     * Appends client maps when the source node is a list/map structure.
     *
     * @param target target list
     * @param node   source node
     */
    private void addClientMaps(List<Map<String, Object>> target, Object node) {
        if (node instanceof List<?> nodeList) {
            for (Object item : nodeList) {
                if (item instanceof Map<?, ?> rawMap) {
                    target.add(this.toStringObjectMap(rawMap));
                }
            }
            return;
        }
        if (node instanceof Map<?, ?> rawMap) {
            target.add(this.toStringObjectMap(rawMap));
        }
    }

    /**
     * Reads a raw system id from a client payload.
     *
     * @param clientPayload client map
     * @return raw system id
     */
    private String readSystemId(Map<String, Object> clientPayload) {
        String systemId = this.asString(clientPayload.get("systemId"));
        if (systemId != null && !systemId.isBlank()) {
            return systemId;
        }
        systemId = this.asString(clientPayload.get("system_id"));
        if (systemId != null && !systemId.isBlank()) {
            return systemId;
        }

        Object nestedSystem = clientPayload.get("system");
        if (nestedSystem instanceof Map<?, ?> nestedSystemMap) {
            String nestedId = this.asString(nestedSystemMap.get("id"));
            if (nestedId != null && !nestedId.isBlank()) {
                return nestedId;
            }
        }

        return this.asString(clientPayload.get("system"));
    }

    /**
     * Converts an unknown map to {@code Map<String, Object>}.
     *
     * @param rawMap source map
     * @return converted map
     */
    private Map<String, Object> toStringObjectMap(Map<?, ?> rawMap) {
        Map<String, Object> converted = new java.util.HashMap<String, Object>();
        for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            converted.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return converted;
    }

    /**
     * Converts an object to string when non-null.
     *
     * @param value source value
     * @return converted value, or {@code null}
     */
    private String asString(Object value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}
