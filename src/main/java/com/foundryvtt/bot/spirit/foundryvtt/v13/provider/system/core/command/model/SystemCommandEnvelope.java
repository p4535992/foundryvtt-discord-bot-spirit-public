package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Typed external envelope for routing a provider command.
 */
public class SystemCommandEnvelope {

    private final String clientId;
    private final String apiKeyOverride;
    private final String commandName;
    private final Map<String, Object> payload;

    @JsonCreator
    public SystemCommandEnvelope(
            @JsonProperty("clientId") String clientId,
            @JsonProperty("apiKeyOverride") String apiKeyOverride,
            @JsonProperty("commandName") String commandName,
            @JsonProperty("payload") Map<String, Object> payload) {
        if (commandName == null || commandName.isBlank()) {
            throw new IllegalArgumentException("commandName must not be blank");
        }
        this.clientId = clientId;
        this.apiKeyOverride = apiKeyOverride;
        this.commandName = commandName;
        if (payload == null) {
            this.payload = Collections.emptyMap();
        } else {
            this.payload = Collections.unmodifiableMap(new HashMap<String, Object>(payload));
        }
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getApiKeyOverride() {
        return this.apiKeyOverride;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Map<String, Object> getPayload() {
        return this.payload;
    }
}
