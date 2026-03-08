package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.routing;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.registry.SystemModuleRegistry;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.spi.SystemModule;

/**
 * Routes generic commands to the correct game-system module.
 */
@ApplicationScoped
public class SystemCommandRouterService {

    /**
     * Resolver used to detect the game system for a relay client.
     */
    private final FoundrySystemResolverService foundrySystemResolverService;

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
            SystemModuleRegistry systemModuleRegistry) {
        this.foundrySystemResolverService = foundrySystemResolverService;
        this.systemModuleRegistry = systemModuleRegistry;
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
