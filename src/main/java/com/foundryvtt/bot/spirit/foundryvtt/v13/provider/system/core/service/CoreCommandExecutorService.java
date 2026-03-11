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
 * Executes provider-level core commands.
 *
 * <p>
 * This service is the core-side dispatcher behind
 * {@link com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing.SystemCommandRouterService}.
 * It takes the generic internal command payload, converts it into a typed request object, calls
 * {@link RestRelayService}, and returns the typed manual model produced by the relay mapper layer.
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

    /**
     * Checks whether a command name belongs to the provider core namespace.
     *
     * @param commandName command identifier
     * @return {@code true} when the command is handled by this executor
     */
    public boolean supportsCommand(String commandName) {
        return CoreCommandNames.isSupported(commandName);
    }

    /**
     * Executes a core command when only client identity and optional API key are known.
     *
     * @param clientId       relay client id
     * @param apiKeyOverride optional API key override
     * @param command        internal command
     * @return typed command result
     */
    public Object execute(String clientId, String apiKeyOverride, SystemCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        return this.executeInternal(clientId, apiKeyOverride, command);
    }

    /**
     * Executes a core command using a pre-resolved world context.
     *
     * <p>
     * The world context is only used to carry client identity and API key for core commands. No
     * system-specific behavior happens in this class.
     *
     * @param worldContext target context
     * @param command      internal command
     * @return typed command result
     */
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

    /**
     * Central {@code if/else} dispatcher for all core commands.
     *
     * <p>
     * Each branch follows the same pattern: payload map -> typed request mapper -> relay service
     * call -> typed manual model returned to the router/web layer.
     *
     * @param clientId       relay client id
     * @param apiKeyOverride optional API key override
     * @param command        internal command
     * @return typed command result
     */
    private Object executeInternal(String clientId, String apiKeyOverride, SystemCommand command) {
        Map<String, Object> payload = command.getPayload();
        String commandName = command.getCommandName();

        if (CoreCommandNames.GET_RELAY_STATUS.equals(commandName)) {
            return this.restRelayService.getRelayStatus();
        }
        if (CoreCommandNames.GET_CONNECTED_CLIENTS.equals(commandName)) {
            return this.restRelayService.getConnectedClients(apiKeyOverride);
        }
        if (CoreCommandNames.GET_CURRENT_SESSIONS.equals(commandName)) {
            return this.restRelayService.getCurrentSessions(apiKeyOverride);
        }
        if (CoreCommandNames.CREATE_SESSION_HANDSHAKE.equals(commandName)) {
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
        if (CoreCommandNames.START_SESSION.equals(commandName)) {
            BodyOnlyCommandRequest request = this.coreCommandRequestMapper
                    .toBodyOnlyRequest(payload);
            return this.restRelayService.startSession(apiKeyOverride, request.requestBody());
        }
        if (CoreCommandNames.END_SESSION.equals(commandName)) {
            SessionIdCommandRequest request = this.coreCommandRequestMapper
                    .toSessionIdRequest(payload);
            return this.restRelayService.endSession(apiKeyOverride, request.sessionId());
        }
        if (CoreCommandNames.GET_STRUCTURE.equals(commandName)) {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getStructure(apiKeyOverride, request.clientId());
        }
        if (CoreCommandNames.GET_ENCOUNTERS.equals(commandName)) {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getEncounters(apiKeyOverride, request.clientId());
        }
        if (CoreCommandNames.GET_ENTITY.equals(commandName)) {
            EntityCommandRequest request = this.coreCommandRequestMapper.toEntityRequest(
                    clientId,
                    payload);
            return this.restRelayService.getEntity(
                    apiKeyOverride,
                    request.clientId(),
                    request.selected(),
                    request.actor());
        }
        if (CoreCommandNames.GET_ENTITY_BY_UUID.equals(commandName)) {
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
        if (CoreCommandNames.GET_ACTOR_SHEET.equals(commandName)) {
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
        if (CoreCommandNames.ROLL.equals(commandName)) {
            RollCommandRequest request = this.coreCommandRequestMapper.toRollRequest(clientId,
                    payload);
            return this.restRelayService.roll(
                    apiKeyOverride,
                    request.clientId(),
                    request.rollRequest());
        }
        if (CoreCommandNames.GET_LAST_ROLL.equals(commandName)) {
            ClientCommandRequest request = this.coreCommandRequestMapper.toClientCommandRequest(
                    clientId,
                    payload);
            return this.restRelayService.getLastRoll(apiKeyOverride, request.clientId());
        }
        if (CoreCommandNames.GET_RECENT_ROLLS.equals(commandName)) {
            RecentRollsCommandRequest request = this.coreCommandRequestMapper.toRecentRollsRequest(
                    clientId,
                    payload);
            return this.restRelayService.getRecentRolls(
                    apiKeyOverride,
                    request.clientId(),
                    request.limit());
        }
        if (CoreCommandNames.SEARCH.equals(commandName)) {
            SearchCommandRequest request = this.coreCommandRequestMapper.toSearchRequest(
                    clientId,
                    payload);
            return this.restRelayService.search(
                    apiKeyOverride,
                    request.clientId(),
                    request.query(),
                    request.filter());
        }
        if (CoreCommandNames.EXECUTE_JAVASCRIPT.equals(commandName)) {
            ClientBodyCommandRequest request = this.coreCommandRequestMapper.toClientBodyRequest(
                    clientId,
                    payload);
            return this.restRelayService.executeJavaScript(
                    apiKeyOverride,
                    request.clientId(),
                    request.requestBody());
        }
        throw new IllegalStateException("Unsupported core command: " + commandName);
    }
}
