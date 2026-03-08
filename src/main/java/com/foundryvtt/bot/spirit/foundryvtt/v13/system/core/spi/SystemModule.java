package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.spi;

import java.util.Set;

import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.Capability;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.WorldContext;

/**
 * Pluggable module contract for game-system specific behavior.
 */
public interface SystemModule {

    /**
     * Returns the supported system id for this module.
     *
     * @return supported system id
     */
    SystemId systemId();

    /**
     * Returns the declared capability set for this module.
     *
     * @return immutable capability set
     */
    Set<Capability> capabilities();

    /**
     * Checks whether the module supports a given command.
     *
     * @param commandName command identifier
     * @return {@code true} when the command is supported
     */
    boolean supportsCommand(String commandName);

    /**
     * Executes a command in the context of the target world.
     *
     * @param command      command envelope
     * @param worldContext target world context
     * @return command result payload
     */
    Object execute(SystemCommand command, WorldContext worldContext);
}
