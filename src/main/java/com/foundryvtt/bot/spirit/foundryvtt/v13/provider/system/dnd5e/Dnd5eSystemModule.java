package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.Capability;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemModule;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.Dnd5eCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service.Dnd5eService;

/**
 * DnD5e system module implementation.
 */
@ApplicationScoped
public class Dnd5eSystemModule implements SystemModule {

    /**
     * DnD5e service facade.
     */
    private final Dnd5eService dnd5eService;

    /**
     * Declared module capabilities.
     */
    private static final Set<Capability> CAPABILITIES = Collections.singleton(
            Capability.SYSTEM_SPECIFIC_DND5E);

    /**
     * Builds the DnD5e module.
     *
     * @param dnd5eService dnd5e service dependency
     */
    @Inject
    public Dnd5eSystemModule(Dnd5eService dnd5eService) {
        this.dnd5eService = dnd5eService;
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
        Map<String, Object> payload = command.getPayload();
        String apiKeyOverride = worldContext.getApiKeyOverride();
        String clientId = worldContext.getClientId();

        switch (command.getCommandName()) {
        case Dnd5eCommandNames.GET_ACTOR_DETAILS: {
            String actorUuid = this.readRequiredString(payload, "actorUuid");
            String details = this.readOptionalString(payload, "details");
            return this.dnd5eService.getActorDetails(
                    apiKeyOverride,
                    clientId,
                    actorUuid,
                    details);
        }
        case Dnd5eCommandNames.MODIFY_EXPERIENCE: {
            String actorUuid = this.readRequiredString(payload, "actorUuid");
            Integer amount = this.readRequiredInteger(payload, "amount");
            Map<String, Object> requestBody = this.readOptionalMap(payload, "requestBody");
            return this.dnd5eService.modifyExperience(
                    apiKeyOverride,
                    clientId,
                    actorUuid,
                    amount,
                    requestBody);
        }
        case Dnd5eCommandNames.USE_ABILITY: {
            String actorUuid = this.readRequiredString(payload, "actorUuid");
            String abilityName = this.readRequiredString(payload, "abilityName");
            Map<String, Object> requestBody = this.readOptionalMap(payload, "requestBody");
            return this.dnd5eService.useAbility(
                    apiKeyOverride,
                    clientId,
                    actorUuid,
                    abilityName,
                    requestBody);
        }
        case Dnd5eCommandNames.MODIFY_ITEM_CHARGES: {
            String actorUuid = this.readRequiredString(payload, "actorUuid");
            String itemName = this.readRequiredString(payload, "itemName");
            String amount = this.readRequiredString(payload, "amount");
            Map<String, Object> requestBody = this.readOptionalMap(payload, "requestBody");
            return this.dnd5eService.modifyItemCharges(
                    apiKeyOverride,
                    clientId,
                    actorUuid,
                    itemName,
                    amount,
                    requestBody);
        }
        default: {
            throw new IllegalStateException(
                    "Unsupported DnD5e command: " + command.getCommandName());
        }
        }
    }

    /**
     * Validates routing context for DnD5e command execution.
     *
     * @param worldContext target context
     */
    private void validateWorldContext(WorldContext worldContext) {
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        if (worldContext.getSystemId() != SystemId.DND5E) {
            throw new IllegalStateException(
                    "DnD5e module cannot execute commands for system: "
                            + worldContext.getSystemId().value());
        }
    }

    /**
     * Reads a required string from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return non-blank string value
     */
    private String readRequiredString(Map<String, Object> payload, String fieldName) {
        String value = this.readOptionalString(payload, fieldName);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        return value;
    }

    /**
     * Reads an optional string from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return optional string value
     */
    private String readOptionalString(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        return String.valueOf(rawValue);
    }

    /**
     * Reads a required integer from command payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return parsed integer value
     */
    private Integer readRequiredInteger(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        if (rawValue instanceof Number) {
            Number numberValue = (Number) rawValue;
            return Integer.valueOf(numberValue.intValue());
        }
        String rawText = String.valueOf(rawValue).trim();
        if (rawText.isEmpty()) {
            throw new IllegalArgumentException("Missing required payload field: " + fieldName);
        }
        try {
            return Integer.valueOf(rawText);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Invalid integer payload field '" + fieldName + "': " + rawText,
                    exception);
        }
    }

    /**
     * Reads an optional nested map payload.
     *
     * @param payload   command payload
     * @param fieldName payload key
     * @return optional nested map
     */
    private Map<String, Object> readOptionalMap(Map<String, Object> payload, String fieldName) {
        Object rawValue = payload.get(fieldName);
        if (rawValue == null) {
            return null;
        }
        if (rawValue instanceof Map<?, ?>) {
            return this.toStringObjectMap((Map<?, ?>) rawValue);
        }
        throw new IllegalArgumentException("Payload field '" + fieldName + "' must be an object");
    }

    /**
     * Converts an unknown map to {@code Map<String, Object>}.
     *
     * @param rawMap source map
     * @return converted map
     */
    private Map<String, Object> toStringObjectMap(Map<?, ?> rawMap) {
        Map<String, Object> converted = new java.util.HashMap<String, Object>();
        for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            converted.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return converted;
    }
}
