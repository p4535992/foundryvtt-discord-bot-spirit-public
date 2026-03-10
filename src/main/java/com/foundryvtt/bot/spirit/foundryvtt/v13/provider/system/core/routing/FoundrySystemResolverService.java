package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.RestRelayService;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayClientInfo;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;

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

        RelayConnectedClientsResult clientsPayload = this.restRelayService
                .getConnectedClients(apiKeyOverride);
        for (RelayClientInfo client : clientsPayload.getClients()) {
            String currentClientId = client.getId();
            if (!clientId.equals(currentClientId)) {
                continue;
            }
            String rawSystemId = this.readSystemId(client);
            return SystemId.fromValue(rawSystemId);
        }
        return SystemId.UNKNOWN;
    }

    /**
     * Reads a raw system id from a client payload.
     *
     * @param clientPayload client model
     * @return raw system id
     */
    private String readSystemId(RelayClientInfo clientPayload) {
        return clientPayload.getSystemId();
    }
}
