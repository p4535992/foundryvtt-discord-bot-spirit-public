package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;

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
}
