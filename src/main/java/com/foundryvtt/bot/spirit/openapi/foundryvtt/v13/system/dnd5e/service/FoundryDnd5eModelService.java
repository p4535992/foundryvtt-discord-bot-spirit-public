package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.service;

import java.util.Optional;
import java.util.Set;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;

public interface FoundryDnd5eModelService {

    Set<String> supportedActorTypes();

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
