package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Typed response envelope for provider command execution.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "SystemCommandResponseEnvelope", description = "Provider command success response envelope.")
public class SystemCommandResponseEnvelope {

    @Schema(description = "Target relay client id.", example = "client-1")
    private final String clientId;
    @Schema(description = "Executed provider command name.", example = "core.getRelayStatus")
    private final String commandName;
    @Schema(description = "Resolved Foundry system id when the command is system-scoped.", example = "dnd5e")
    private final String systemId;
    @Schema(description = "Whether the command was handled by the core provider layer.")
    private final Boolean coreCommand;
    @Schema(description = "Command result payload.")
    private final Object result;
    @Schema(description = "Additional routing metadata.")
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
