package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.model;

import java.util.Map;

/**
 * Typed provider request models for DnD5e commands.
 */
public final class Dnd5eCommandRequests {

    private Dnd5eCommandRequests() {
    }

    public record ActorDetailsCommandRequest(String actorUuid, String details) {
    }

    public record ModifyExperienceCommandRequest(
            String actorUuid,
            Integer amount,
            Map<String, Object> requestBody) {
    }

    public record UseAbilityCommandRequest(
            String actorUuid,
            String abilityName,
            Map<String, Object> requestBody) {
    }

    public record ModifyItemChargesCommandRequest(
            String actorUuid,
            String itemName,
            String amount,
            Map<String, Object> requestBody) {
    }
}
