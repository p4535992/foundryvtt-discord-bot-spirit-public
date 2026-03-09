package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * DnD5e command identifiers routed by {@code SystemCommandRouterService}.
 */
public final class Dnd5eCommandNames {

    /**
     * Command: read actor details.
     */
    public static final String GET_ACTOR_DETAILS = "dnd5e.getActorDetails";

    /**
     * Command: modify actor experience points.
     */
    public static final String MODIFY_EXPERIENCE = "dnd5e.modifyExperience";

    /**
     * Command: execute an ability usage.
     */
    public static final String USE_ABILITY = "dnd5e.useAbility";

    /**
     * Command: modify charges for an item.
     */
    public static final String MODIFY_ITEM_CHARGES = "dnd5e.modifyItemCharges";

    /**
     * Immutable set of all supported command names.
     */
    private static final Set<String> SUPPORTED_COMMANDS = Collections.unmodifiableSet(
            new HashSet<String>(
                    Arrays.asList(
                            GET_ACTOR_DETAILS,
                            MODIFY_EXPERIENCE,
                            USE_ABILITY,
                            MODIFY_ITEM_CHARGES)));

    /**
     * Utility class constructor.
     */
    private Dnd5eCommandNames() {
        // Utility class.
    }

    /**
     * Returns the immutable set of supported command names.
     *
     * @return supported commands
     */
    public static Set<String> supportedCommands() {
        return SUPPORTED_COMMANDS;
    }

    /**
     * Checks if a command name is supported by the DnD5e module.
     *
     * @param commandName command identifier
     * @return {@code true} when supported
     */
    public static boolean isSupported(String commandName) {
        if (commandName == null || commandName.isBlank()) {
            return false;
        }
        return SUPPORTED_COMMANDS.contains(commandName);
    }
}
