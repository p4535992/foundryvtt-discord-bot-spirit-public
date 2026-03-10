package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.Dnd5eCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ActorDetailsCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ModifyExperienceCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ModifyItemChargesCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.UseAbilityCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.mapper.Dnd5eCommandRequestMapper;

/**
 * Executes DnD5e provider commands and returns typed domain models.
 */
@ApplicationScoped
public class Dnd5eCommandExecutorService {

    private final Dnd5eService dnd5eService;
    private final Dnd5eCommandRequestMapper dnd5eCommandRequestMapper;

    @Inject
    public Dnd5eCommandExecutorService(
            Dnd5eService dnd5eService,
            Dnd5eCommandRequestMapper dnd5eCommandRequestMapper) {
        this.dnd5eService = dnd5eService;
        this.dnd5eCommandRequestMapper = dnd5eCommandRequestMapper;
    }

    public boolean supportsCommand(String commandName) {
        return Dnd5eCommandNames.isSupported(commandName);
    }

    public Object execute(WorldContext worldContext, SystemCommand command) {
        if (worldContext == null) {
            throw new IllegalArgumentException("worldContext must not be null");
        }
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }

        Map<String, Object> payload = command.getPayload();
        String apiKeyOverride = worldContext.getApiKeyOverride();
        String clientId = worldContext.getClientId();

        switch (command.getCommandName()) {
        case Dnd5eCommandNames.GET_ACTOR_DETAILS: {
            ActorDetailsCommandRequest request = this.dnd5eCommandRequestMapper
                    .toActorDetailsRequest(payload);
            return this.dnd5eService.getActorDetails(
                    apiKeyOverride,
                    clientId,
                    request.actorUuid(),
                    request.details());
        }
        case Dnd5eCommandNames.MODIFY_EXPERIENCE: {
            ModifyExperienceCommandRequest request = this.dnd5eCommandRequestMapper
                    .toModifyExperienceRequest(payload);
            return this.dnd5eService.modifyExperience(
                    apiKeyOverride,
                    clientId,
                    request.actorUuid(),
                    request.amount(),
                    request.requestBody());
        }
        case Dnd5eCommandNames.USE_ABILITY: {
            UseAbilityCommandRequest request = this.dnd5eCommandRequestMapper
                    .toUseAbilityRequest(payload);
            return this.dnd5eService.useAbility(
                    apiKeyOverride,
                    clientId,
                    request.actorUuid(),
                    request.abilityName(),
                    request.requestBody());
        }
        case Dnd5eCommandNames.MODIFY_ITEM_CHARGES: {
            ModifyItemChargesCommandRequest request = this.dnd5eCommandRequestMapper
                    .toModifyItemChargesRequest(payload);
            return this.dnd5eService.modifyItemCharges(
                    apiKeyOverride,
                    clientId,
                    request.actorUuid(),
                    request.itemName(),
                    request.amount(),
                    request.requestBody());
        }
        default:
            throw new IllegalStateException(
                    "Unsupported DnD5e command: " + command.getCommandName());
        }
    }
}
