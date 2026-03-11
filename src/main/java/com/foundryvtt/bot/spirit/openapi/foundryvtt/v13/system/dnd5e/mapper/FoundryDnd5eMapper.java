package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.mapper;

import java.util.Optional;
import java.util.Set;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;

/**
 * Conversion entry point between relay payloads and hand-written Foundry dnd5e models.
 *
 * <p>This mapper sits on top of the core model layer and materializes dnd5e
 * specific system data from relay payloads, while keeping generated relay
 * classes out of the provider and command layers.
 */
public interface FoundryDnd5eMapper {

    /**
     * Returns the actor types with explicit dnd5e system-data models.
     *
     * @return supported actor types
     */
    Set<String> supportedActorTypes();

    /**
     * Returns the item types with explicit dnd5e system-data models.
     *
     * @return supported item types
     */
    Set<String> supportedItemTypes();

    Optional<Class<? extends Dnd5eActorSystemData>> findActorSystemClass(String actorType);

    Optional<Class<? extends Dnd5eItemSystemData>> findItemSystemClass(String itemType);

    Dnd5eActorDocument toActorDocument(Object payload);

    Dnd5eItemDocument toItemDocument(Object payload);

    Dnd5eModifyExperienceResult toModifyExperienceResult(Object payload);

    Dnd5eUseAbilityResult toUseAbilityResult(Object payload);

    Dnd5eModifyItemChargesResult toModifyItemChargesResult(Object payload);

    Dnd5eActorSystemData toActorSystem(String actorType, Object payload);

    Dnd5eItemSystemData toItemSystem(String itemType, Object payload);
}
