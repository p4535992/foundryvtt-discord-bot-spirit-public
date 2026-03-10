package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service;

import java.util.Map;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;

/**
 * DnD5e-specific service facade over relay REST endpoints.
 */
public interface Dnd5eService {

    /**
     * Reads details for a DnD5e actor.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param actorUuid      actor UUID
     * @param details        optional details selector
     * @return typed actor payload
     */
    Dnd5eActorDocument getActorDetails(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String details);

    /**
     * Modifies DnD5e actor experience.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param actorUuid      actor UUID
     * @param amount         amount delta
     * @param requestBody    optional request payload
     * @return operation result payload
     */
    Dnd5eModifyExperienceResult modifyExperience(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            Integer amount,
            Map<String, Object> requestBody);

    /**
     * Uses a DnD5e ability for an actor.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param actorUuid      actor UUID
     * @param abilityName    ability name
     * @param requestBody    optional request payload
     * @return operation result payload
     */
    Dnd5eUseAbilityResult useAbility(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String abilityName,
            Map<String, Object> requestBody);

    /**
     * Modifies DnD5e item charges for an actor.
     *
     * @param apiKeyOverride optional API key override
     * @param clientId       relay client id
     * @param actorUuid      actor UUID
     * @param itemName       item name
     * @param amount         amount delta
     * @param requestBody    optional request payload
     * @return operation result payload
     */
    Dnd5eModifyItemChargesResult modifyItemCharges(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String itemName,
            String amount,
            Map<String, Object> requestBody);
}
