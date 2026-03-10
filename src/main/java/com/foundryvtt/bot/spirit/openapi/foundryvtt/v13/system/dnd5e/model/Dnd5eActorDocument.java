package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dnd5eActorDocument extends FoundryActorDocument {

    private Dnd5eActorSystemData systemData;

    public Dnd5eActorSystemData getSystemData() {
        return this.systemData;
    }

    public void setSystemData(Dnd5eActorSystemData systemData) {
        this.systemData = systemData;
    }

    public List<Dnd5eItemDocument> getTypedItems() {
        List<Dnd5eItemDocument> typedItems = new ArrayList<Dnd5eItemDocument>(this.getItems().size());
        for (FoundryItemDocument item : this.getItems()) {
            if (item instanceof Dnd5eItemDocument typedItem) {
                typedItems.add(typedItem);
            }
        }
        return typedItems;
    }

    public void setTypedItems(List<Dnd5eItemDocument> items) {
        this.setItems(new ArrayList<FoundryItemDocument>(items));
    }
}
