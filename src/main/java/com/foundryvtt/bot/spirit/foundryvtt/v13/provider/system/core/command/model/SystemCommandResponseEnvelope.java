package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Typed response envelope for provider command execution.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemCommandResponseEnvelope {

    private final String clientId;
    private final String commandName;
    private final String systemId;
    private final Boolean coreCommand;
    private final Object result;
    private final Map<String, Object> metadata;

    public SystemCommandResponseEnvelope(
            String clientId,
            String commandName,
            String systemId,
            Boolean coreCommand,
            Object result,
            Map<String, Object> metadata) {
        this.clientId = clientId;
        this.commandName = commandName;
        this.systemId = systemId;
        this.coreCommand = coreCommand;
        this.result = result;
        if (metadata == null) {
            this.metadata = Collections.emptyMap();
        } else {
            this.metadata = Collections.unmodifiableMap(new HashMap<String, Object>(metadata));
        }
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public Boolean getCoreCommand() {
        return this.coreCommand;
    }

    public Object getResult() {
        return this.result;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }
}
