package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.mapper.AbstractFoundryMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eCharacterActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eNpcActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eRelayOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eSpellItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eWeaponItemSystemData;

@ApplicationScoped
public class FoundryDnd5eMapperImpl extends AbstractFoundryMapper implements FoundryDnd5eMapper {

    private final Map<String, Class<? extends Dnd5eActorSystemData>> actorSystemClasses;
    private final Map<String, Class<? extends Dnd5eItemSystemData>> itemSystemClasses;

    @Inject
    public FoundryDnd5eMapperImpl(ObjectMapper objectMapper) {
        super(objectMapper);

        Map<String, Class<? extends Dnd5eActorSystemData>> actorTypes =
                new LinkedHashMap<String, Class<? extends Dnd5eActorSystemData>>();
        actorTypes.put("character", Dnd5eCharacterActorSystemData.class);
        actorTypes.put("npc", Dnd5eNpcActorSystemData.class);
        this.actorSystemClasses = Collections.unmodifiableMap(actorTypes);

        Map<String, Class<? extends Dnd5eItemSystemData>> itemTypes =
                new LinkedHashMap<String, Class<? extends Dnd5eItemSystemData>>();
        itemTypes.put("spell", Dnd5eSpellItemSystemData.class);
        itemTypes.put("weapon", Dnd5eWeaponItemSystemData.class);
        this.itemSystemClasses = Collections.unmodifiableMap(itemTypes);
    }

    @Override
    public Set<String> supportedActorTypes() {
        return this.actorSystemClasses.keySet();
    }

    @Override
    public Set<String> supportedItemTypes() {
        return this.itemSystemClasses.keySet();
    }

    @Override
    public Optional<Class<? extends Dnd5eActorSystemData>> findActorSystemClass(String actorType) {
        if (actorType == null || actorType.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.actorSystemClasses.get(this.normalizeType(actorType)));
    }

    @Override
    public Optional<Class<? extends Dnd5eItemSystemData>> findItemSystemClass(String itemType) {
        if (itemType == null || itemType.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.itemSystemClasses.get(this.normalizeType(itemType)));
    }

    @Override
    public Dnd5eActorDocument toActorDocument(Object payload) {
        if (payload == null) {
            return null;
        }
        Dnd5eActorDocument actor = this.convert(payload, Dnd5eActorDocument.class);
        actor.setSystemData(this.toActorSystem(actor.getType(), actor.getSystem()));
        if (actor.getItems() != null && !actor.getItems().isEmpty()) {
            List<Dnd5eItemDocument> convertedItems = new ArrayList<Dnd5eItemDocument>(actor.getItems().size());
            for (FoundryItemDocument item : actor.getItems()) {
                convertedItems.add(this.toItemDocument(item));
            }
            actor.setTypedItems(convertedItems);
        }
        return actor;
    }

    @Override
    public Dnd5eItemDocument toItemDocument(Object payload) {
        if (payload == null) {
            return null;
        }
        Dnd5eItemDocument item = this.convert(payload, Dnd5eItemDocument.class);
        item.setSystemData(this.toItemSystem(item.getType(), item.getSystem()));
        return item;
    }

    @Override
    public Dnd5eModifyExperienceResult toModifyExperienceResult(Object payload) {
        return this.convertOperationResult(
                payload,
                Dnd5eModifyExperienceResult.class,
                "actor",
                null,
                null);
    }

    @Override
    public Dnd5eUseAbilityResult toUseAbilityResult(Object payload) {
        return this.convertOperationResult(
                payload,
                Dnd5eUseAbilityResult.class,
                "actor",
                "ability",
                "item");
    }

    @Override
    public Dnd5eModifyItemChargesResult toModifyItemChargesResult(Object payload) {
        return this.convertOperationResult(
                payload,
                Dnd5eModifyItemChargesResult.class,
                "actor",
                "item",
                null);
    }

    @Override
    public Dnd5eActorSystemData toActorSystem(String actorType, Object payload) {
        return this.convert(payload, this.findActorSystemClass(actorType).orElse(Dnd5eActorSystemData.class));
    }

    @Override
    public Dnd5eItemSystemData toItemSystem(String itemType, Object payload) {
        return this.convert(payload, this.findItemSystemClass(itemType).orElse(Dnd5eItemSystemData.class));
    }

    private <T extends Dnd5eRelayOperationResult> T convertOperationResult(
            Object payload,
            Class<T> targetType,
            String actorKey,
            String itemKey,
            String fallbackItemKey) {
        if (payload == null) {
            return null;
        }
        T result = this.convert(payload, targetType);
        if (!(payload instanceof Map<?, ?> rawMap)) {
            return result;
        }

        Object actorPayload = rawMap.get(actorKey);
        if (actorPayload != null) {
            this.setActorIfSupported(result, actorPayload);
        }

        Object itemPayload = this.firstNonNull(rawMap, itemKey, fallbackItemKey);
        if (itemPayload != null) {
            this.setItemIfSupported(result, itemPayload);
        }
        return result;
    }

    private Object firstNonNull(Map<?, ?> rawMap, String primaryKey, String secondaryKey) {
        if (primaryKey != null) {
            Object primaryValue = rawMap.get(primaryKey);
            if (primaryValue != null) {
                return primaryValue;
            }
        }
        if (secondaryKey != null) {
            return rawMap.get(secondaryKey);
        }
        return null;
    }

    private void setActorIfSupported(Dnd5eRelayOperationResult result, Object actorPayload) {
        Dnd5eActorDocument actor = this.toActorDocument(actorPayload);
        if (result instanceof Dnd5eModifyExperienceResult modifyExperienceResult) {
            modifyExperienceResult.setActor(actor);
        } else if (result instanceof Dnd5eUseAbilityResult useAbilityResult) {
            useAbilityResult.setActor(actor);
        } else if (result instanceof Dnd5eModifyItemChargesResult modifyItemChargesResult) {
            modifyItemChargesResult.setActor(actor);
        }
    }

    private void setItemIfSupported(Dnd5eRelayOperationResult result, Object itemPayload) {
        Dnd5eItemDocument item = this.toItemDocument(itemPayload);
        if (result instanceof Dnd5eUseAbilityResult useAbilityResult) {
            useAbilityResult.setAbility(item);
        } else if (result instanceof Dnd5eModifyItemChargesResult modifyItemChargesResult) {
            modifyItemChargesResult.setItem(item);
        }
    }
}
