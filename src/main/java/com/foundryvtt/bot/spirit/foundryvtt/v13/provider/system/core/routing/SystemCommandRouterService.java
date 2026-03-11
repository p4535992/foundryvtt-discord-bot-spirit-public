package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandResponseEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper.SystemCommandEnvelopeMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.registry.SystemModuleRegistry;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.CoreCommandExecutorService;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemModule;

/**
 * Routes generic commands to the correct game-system module.
 */
@ApplicationScoped
public class SystemCommandRouterService {

    /**
     * Resolver used to detect the game system for a relay client.
     */
    private final FoundrySystemResolverService foundrySystemResolverService;
    private final CoreCommandExecutorService coreCommandExecutorService;
    private final SystemCommandEnvelopeMapper systemCommandEnvelopeMapper;

    /**
     * Registry of available modules.
     */
    private final SystemModuleRegistry systemModuleRegistry;

    /**
     * Builds the router service.
     *
     * @param foundrySystemResolverService service used to resolve system ids
     * @param systemModuleRegistry         registry of module implementations
     */
    @Inject
    public SystemCommandRouterService(
            FoundrySystemResolverService foundrySystemResolverService,
            CoreCommandExecutorService coreCommandExecutorService,
            SystemCommandEnvelopeMapper systemCommandEnvelopeMapper,
            SystemModuleRegistry systemModuleRegistry) {
        this.foundrySystemResolverService = foundrySystemResolverService;
        this.coreCommandExecutorService = coreCommandExecutorService;
        this.systemCommandEnvelopeMapper = systemCommandEnvelopeMapper;
        this.systemModuleRegistry = systemModuleRegistry;
    }

    /**
     * Routes an external typed command envelope.
     *
     * @param envelope external command envelope
     * @return command result payload
     */
    public Object route(SystemCommandEnvelope envelope) {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }
        return this.route(
                envelope.getClientId(),
                envelope.getApiKeyOverride(),
                this.systemCommandEnvelopeMapper.toSystemCommand(envelope));
    }

    /**
     * Routes an external typed command envelope and wraps the result in a typed response.
     *
     * @param envelope external command envelope
     * @return typed command response envelope
     */
    public SystemCommandResponseEnvelope routeForResponse(SystemCommandEnvelope envelope) {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }
        SystemCommand command = this.systemCommandEnvelopeMapper.toSystemCommand(envelope);
        if (this.coreCommandExecutorService.supportsCommand(command.getCommandName())) {
            Object result = this.coreCommandExecutorService.execute(
                    envelope.getClientId(),
                    envelope.getApiKeyOverride(),
                    command);
            Map<String, Object> metadata = new HashMap<String, Object>();
            metadata.put("scope", "core");
            return new SystemCommandResponseEnvelope(
                    envelope.getClientId(),
                    command.getCommandName(),
                    null,
                    Boolean.TRUE,
                    result,
                    metadata);
        }

        WorldContext worldContext = this.resolveWorldContext(
                envelope.getClientId(),
                envelope.getApiKeyOverride());
        Object result = this.route(worldContext, command);
        Map<String, Object> metadata = new HashMap<String, Object>(worldContext.getMetadata());
        metadata.put("scope", "system");
        return new SystemCommandResponseEnvelope(
                worldContext.getClientId(),
                command.getCommandName(),
                worldContext.getSystemId().value(),
                Boolean.FALSE,
                result,
                metadata);
    }

    /**
     * Routes a command to the module associated with the provided relay client.
     *
     * @param clientId       relay client id
     * @param apiKeyOverride optional API key override
     * @param command        command envelope
     * @return command result payload
     */
    public Object route(String clientId, String apiKeyOverride, SystemCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        if (this.coreCommandExecutorService.supportsCommand(command.getCommandName())) {
            return this.coreCommandExecutorService.execute(clientId, apiKeyOverride, command);
        }
        WorldContext worldContext = this.resolveWorldContext(clientId, apiKeyOverride);
        return this.route(worldContext, command);
    }

    /**
     * Routes a command using a pre-resolved world context.
     *
     * @param worldContext target world context
     * @param command      command envelope
     * @return command result payload
     */
    public Object route(WorldContext worldContext, SystemCommand command) {
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        if (this.coreCommandExecutorService.supportsCommand(command.getCommandName())) {
            return this.coreCommandExecutorService.execute(worldContext, command);
        }

        SystemModule systemModule = this.systemModuleRegistry
                .getRequiredModule(worldContext.getSystemId());
        if (!systemModule.supportsCommand(command.getCommandName())) {
            throw new IllegalStateException(
                    "Unsupported command '"
                            + command.getCommandName()
                            + "' for system '"
                            + worldContext.getSystemId().value()
                            + "'.");
        }
        return systemModule.execute(command, worldContext);
    }

    /**
     * Resolves a world context for a relay client.
     *
     * @param clientId       relay client id
     * @param apiKeyOverride optional API key override
     * @return resolved world context
     */
    public WorldContext resolveWorldContext(String clientId, String apiKeyOverride) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("clientId must not be blank");
        }
        SystemId systemId = this.foundrySystemResolverService.resolveSystemIdForClient(clientId,
                apiKeyOverride);
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("resolver", FoundrySystemResolverService.class.getSimpleName());
        metadata.put("systemId", systemId.value());
        return new WorldContext(clientId, apiKeyOverride, systemId, metadata);
    }
}
