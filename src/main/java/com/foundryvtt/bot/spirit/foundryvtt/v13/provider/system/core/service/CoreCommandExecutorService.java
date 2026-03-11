package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.CoreCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ActorSheetCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.BodyOnlyCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ClientBodyCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ClientCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.EntityByUuidCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.EntityCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.RecentRollsCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.RollCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SearchCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SessionHandshakeCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SessionIdCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper.CoreCommandRequestMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;

/**
 * Executes provider-level core relay commands and returns typed relay/domain models.
 */
@ApplicationScoped
public class CoreCommandExecutorService {

    private final RestRelayService restRelayService;
    private final CoreCommandRequestMapper coreCommandRequestMapper;

    @Inject
    public CoreCommandExecutorService(RestRelayService restRelayService,
            CoreCommandRequestMapper coreCommandRequestMapper) {
        this.restRelayService = restRelayService;
        this.coreCommandRequestMapper = coreCommandRequestMapper;
    }

    public boolean supportsCommand(String commandName) {
        return CoreCommandNames.isSupported(commandName);
    }

    public Object execute(String clientId, String apiKeyOverride, SystemCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        return this.executeInternal(clientId, apiKeyOverride, command);
    }

    public Object execute(WorldContext worldContext, SystemCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        return this.executeInternal(
                worldContext.getClientId(),
                worldContext.getApiKeyOverride(),
                command);
    }

    private Object executeInternal(String clientId, String apiKeyOverride, SystemCommand command) {
        Map<String, Object> payload = command.getPayload();

        switch (command.getCommandName()) {
        case CoreCommandNames.GET_RELAY_STATUS -> {
            return this.restRelayService.getRelayStatus();
        }
        case CoreCommandNames.GET_CONNECTED_CLIENTS -> {
            return this.restRelayService.getConnectedClients(apiKeyOverride);
        }
        case CoreCommandNames.GET_CURRENT_SESSIONS -> {
            return this.restRelayService.getCurrentSessions(apiKeyOverride);
        }
        case CoreCommandNames.CREATE_SESSION_HANDSHAKE -> {
            SessionHandshakeCommandRequest request = this.coreCommandRequestMapper
                    .toSessionHandshakeRequest(payload);
            return this.restRelayService.createSessionHandshake(
                    apiKeyOverride,
                    request.foundryUrl(),
                    request.username(),
                    request.password(),
                    request.worldName(),
                    request.requestBody());
        }
        case CoreCommandNames.START_SESSION -> {
            BodyOnlyCommandRequest request = this.coreCommandRequestMapper
                    .toBodyOnlyRequest(payload);
            return this.restRelayService.startSession(apiKeyOverride, request.requestBody());
        }
        case CoreCommandNames.END_SESSION -> {
            SessionIdCommandRequest request = this.coreCommandRequestMapper
                    .toSessionIdRequest(payload);
            return this.restRelayService.endSession(apiKeyOverride, request.sessionId());
        }
        case CoreCommandNames.GET_STRUCTURE -> {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getStructure(apiKeyOverride, request.clientId());
        }
        case CoreCommandNames.GET_ENCOUNTERS -> {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getEncounters(apiKeyOverride, request.clientId());
        }
        case CoreCommandNames.GET_ENTITY -> {
            EntityCommandRequest request = this.coreCommandRequestMapper.toEntityRequest(
                    clientId,
                    payload);
            return this.restRelayService.getEntity(
                    apiKeyOverride,
                    request.clientId(),
                    request.selected(),
                    request.actor());
        }
        case CoreCommandNames.GET_ENTITY_BY_UUID -> {
            EntityByUuidCommandRequest request = this.coreCommandRequestMapper
                    .toEntityByUuidRequest(
                            clientId,
                            payload);
            return this.restRelayService.getEntityByUuid(
                    apiKeyOverride,
                    request.clientId(),
                    request.uuid(),
                    request.actor());
        }
        case CoreCommandNames.GET_ACTOR_SHEET -> {
            ActorSheetCommandRequest request = this.coreCommandRequestMapper.toActorSheetRequest(
                    clientId,
                    payload);
            return this.restRelayService.getActorSheet(
                    apiKeyOverride,
                    request.clientId(),
                    request.uuid(),
                    request.selected(),
                    request.actor(),
                    request.scale());
        }
        case CoreCommandNames.ROLL -> {
            RollCommandRequest request = this.coreCommandRequestMapper.toRollRequest(clientId,
                    payload);
            return this.restRelayService.roll(
                    apiKeyOverride,
                    request.clientId(),
                    request.rollRequest());
        }
        case CoreCommandNames.GET_LAST_ROLL -> {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getLastRoll(apiKeyOverride, request.clientId());
        }
        case CoreCommandNames.GET_RECENT_ROLLS -> {
            RecentRollsCommandRequest request = this.coreCommandRequestMapper.toRecentRollsRequest(
                    clientId,
                    payload);
            return this.restRelayService.getRecentRolls(
                    apiKeyOverride,
                    request.clientId(),
                    request.limit());
        }
        case CoreCommandNames.SEARCH -> {
            SearchCommandRequest request = this.coreCommandRequestMapper.toSearchRequest(
                    clientId,
                    payload);
            return this.restRelayService.search(
                    apiKeyOverride,
                    request.clientId(),
                    request.query(),
                    request.filter());
        }
        case CoreCommandNames.EXECUTE_JAVASCRIPT -> {
            ClientBodyCommandRequest request = this.coreCommandRequestMapper.toClientBodyRequest(
                    clientId,
                    payload);
            return this.restRelayService.executeJavaScript(
                    apiKeyOverride,
                    request.clientId(),
                    request.requestBody());
        }
        default -> throw new IllegalStateException(
                "Unsupported core command: " + command.getCommandName());
        }
    }
}
