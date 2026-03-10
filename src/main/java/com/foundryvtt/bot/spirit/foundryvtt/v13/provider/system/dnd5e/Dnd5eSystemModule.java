package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e;

import java.util.Collections;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.Capability;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.AbstractSystemModule;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.Dnd5eCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service.Dnd5eCommandExecutorService;

/**
 * DnD5e system module implementation.
 */
@ApplicationScoped
public class Dnd5eSystemModule extends AbstractSystemModule {

    /**
     * DnD5e service facade.
     */
    private final Dnd5eCommandExecutorService dnd5eCommandExecutorService;

    /**
     * Declared module capabilities.
     */
    private static final Set<Capability> CAPABILITIES = Collections.singleton(
            Capability.SYSTEM_SPECIFIC_DND5E);

    /**
     * Builds the DnD5e module.
     *
     * @param dnd5eCommandExecutorService command executor dependency
     */
    @Inject
    public Dnd5eSystemModule(Dnd5eCommandExecutorService dnd5eCommandExecutorService) {
        this.dnd5eCommandExecutorService = dnd5eCommandExecutorService;
    }

    @Override
    public SystemId systemId() {
        return SystemId.DND5E;
    }

    @Override
    public Set<Capability> capabilities() {
        return CAPABILITIES;
    }

    @Override
    public boolean supportsCommand(String commandName) {
        return Dnd5eCommandNames.isSupported(commandName);
    }

    @Override
    public Object execute(SystemCommand command, WorldContext worldContext) {
        this.validateWorldContext(worldContext);
        return this.dnd5eCommandExecutorService.execute(worldContext, command);
    }

}
