package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic command envelope routed to a system module.
 */
public class SystemCommand {

    /**
     * Command identifier.
     */
    private final String commandName;

    /**
     * Command payload.
     */
    private final Map<String, Object> payload;

    /**
     * Creates a command with an empty payload.
     *
     * @param commandName command identifier
     */
    public SystemCommand(String commandName) {
        this(commandName, null);
    }

    /**
     * Creates a command envelope.
     *
     * @param commandName command identifier
     * @param payload     command payload
     */
    public SystemCommand(String commandName, Map<String, Object> payload) {
        if (commandName == null || commandName.isBlank()) {
            throw new IllegalArgumentException("commandName must not be blank");
        }
        this.commandName = commandName;
        if (payload == null) {
            this.payload = Collections.emptyMap();
        } else {
            this.payload = Collections.unmodifiableMap(new HashMap<String, Object>(payload));
        }
    }

    /**
     * Returns the command identifier.
     *
     * @return command name
     */
    public String getCommandName() {
        return this.commandName;
    }

    /**
     * Returns an immutable payload map.
     *
     * @return payload map
     */
    public Map<String, Object> getPayload() {
        return this.payload;
    }
}
