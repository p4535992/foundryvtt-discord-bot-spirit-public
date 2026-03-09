package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dnd5eItemDocument extends FoundryItemDocument {

    private Dnd5eItemSystemData systemData;

    public Dnd5eItemSystemData getSystemData() {
        return this.systemData;
    }

    public void setSystemData(Dnd5eItemSystemData systemData) {
        this.systemData = systemData;
    }
}
