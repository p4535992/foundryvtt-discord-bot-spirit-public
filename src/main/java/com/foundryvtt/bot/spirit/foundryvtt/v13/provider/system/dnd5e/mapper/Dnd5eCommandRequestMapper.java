package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.mapper;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper.AbstractCommandRequestMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ActorDetailsCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ModifyExperienceCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.ModifyItemChargesCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model.Dnd5eCommandRequests.UseAbilityCommandRequest;

/**
 * Maps raw DnD5e command payloads to typed provider request models.
 */
@ApplicationScoped
public class Dnd5eCommandRequestMapper extends AbstractCommandRequestMapper {

    @Inject
    public Dnd5eCommandRequestMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public ActorDetailsCommandRequest toActorDetailsRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ActorDetailsCommandRequest(
                this.readRequiredString(requestPayload, "actorUuid"),
                this.readOptionalString(requestPayload, "details"));
    }

    public ModifyExperienceCommandRequest toModifyExperienceRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ModifyExperienceCommandRequest(
                this.readRequiredString(requestPayload, "actorUuid"),
                this.readRequiredInteger(requestPayload, "amount"),
                this.readOptionalMap(requestPayload, "requestBody"));
    }

    public UseAbilityCommandRequest toUseAbilityRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new UseAbilityCommandRequest(
                this.readRequiredString(requestPayload, "actorUuid"),
                this.readRequiredString(requestPayload, "abilityName"),
                this.readOptionalMap(requestPayload, "requestBody"));
    }

    public ModifyItemChargesCommandRequest toModifyItemChargesRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ModifyItemChargesCommandRequest(
                this.readRequiredString(requestPayload, "actorUuid"),
                this.readRequiredString(requestPayload, "itemName"),
                this.readRequiredString(requestPayload, "amount"),
                this.readOptionalMap(requestPayload, "requestBody"));
    }
}
