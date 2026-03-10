package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Core relay command identifiers routed independently from game-system modules.
 */
public final class CoreCommandNames {

    public static final String GET_RELAY_STATUS = "core.getRelayStatus";
    public static final String GET_CONNECTED_CLIENTS = "core.getConnectedClients";
    public static final String GET_CURRENT_SESSIONS = "core.getCurrentSessions";
    public static final String CREATE_SESSION_HANDSHAKE = "core.createSessionHandshake";
    public static final String START_SESSION = "core.startSession";
    public static final String END_SESSION = "core.endSession";
    public static final String GET_STRUCTURE = "core.getStructure";
    public static final String GET_ENCOUNTERS = "core.getEncounters";
    public static final String GET_ENTITY = "core.getEntity";
    public static final String GET_ENTITY_BY_UUID = "core.getEntityByUuid";
    public static final String GET_ACTOR_SHEET = "core.getActorSheet";
    public static final String ROLL = "core.roll";
    public static final String GET_LAST_ROLL = "core.getLastRoll";
    public static final String GET_RECENT_ROLLS = "core.getRecentRolls";
    public static final String SEARCH = "core.search";
    public static final String EXECUTE_JAVASCRIPT = "core.executeJavaScript";

    private static final Set<String> SUPPORTED_COMMANDS = Collections.unmodifiableSet(
            new HashSet<String>(
                    Arrays.asList(
                            GET_RELAY_STATUS,
                            GET_CONNECTED_CLIENTS,
                            GET_CURRENT_SESSIONS,
                            CREATE_SESSION_HANDSHAKE,
                            START_SESSION,
                            END_SESSION,
                            GET_STRUCTURE,
                            GET_ENCOUNTERS,
                            GET_ENTITY,
                            GET_ENTITY_BY_UUID,
                            GET_ACTOR_SHEET,
                            ROLL,
                            GET_LAST_ROLL,
                            GET_RECENT_ROLLS,
                            SEARCH,
                            EXECUTE_JAVASCRIPT)));

    private CoreCommandNames() {
        // Utility class.
    }

    public static boolean isSupported(String commandName) {
        if (commandName == null || commandName.isBlank()) {
            return false;
        }
        return SUPPORTED_COMMANDS.contains(commandName);
    }

    public static Set<String> supportedCommands() {
        return SUPPORTED_COMMANDS;
    }
}
