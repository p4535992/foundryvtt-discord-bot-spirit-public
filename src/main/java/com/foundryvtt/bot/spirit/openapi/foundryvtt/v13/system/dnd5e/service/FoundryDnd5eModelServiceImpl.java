package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eCharacterActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eNpcActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eSpellItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eWeaponItemSystemData;

@ApplicationScoped
public class FoundryDnd5eModelServiceImpl implements FoundryDnd5eModelService {

    private final ObjectMapper objectMapper;
    private final Map<String, Class<? extends Dnd5eActorSystemData>> actorSystemClasses;
    private final Map<String, Class<? extends Dnd5eItemSystemData>> itemSystemClasses;

    @Inject
    public FoundryDnd5eModelServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

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
            List<FoundryItemDocument> convertedItems = new ArrayList<FoundryItemDocument>(actor.getItems().size());
            for (FoundryItemDocument item : actor.getItems()) {
                convertedItems.add(this.toItemDocument(item));
            }
            actor.setItems(convertedItems);
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
    public Dnd5eActorSystemData toActorSystem(String actorType, Object payload) {
        return this.convert(payload, this.findActorSystemClass(actorType).orElse(Dnd5eActorSystemData.class));
    }

    @Override
    public Dnd5eItemSystemData toItemSystem(String itemType, Object payload) {
        return this.convert(payload, this.findItemSystemClass(itemType).orElse(Dnd5eItemSystemData.class));
    }

    private <T> T convert(Object payload, Class<T> targetType) {
        if (payload == null) {
            return null;
        }
        if (targetType.isInstance(payload)) {
            return targetType.cast(payload);
        }
        return this.objectMapper.convertValue(payload, targetType);
    }

    private String normalizeType(String value) {
        return value
                .replace("-", "")
                .replace("_", "")
                .replace(" ", "")
                .toLowerCase(Locale.ROOT);
    }
}
