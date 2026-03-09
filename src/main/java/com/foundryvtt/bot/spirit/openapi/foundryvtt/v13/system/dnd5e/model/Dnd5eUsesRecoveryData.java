package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eUsesRecoveryData extends AbstractDnd5eModel {

    private String period;
    private String type;
    private JsonNode formula;

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getFormula() {
        return this.formula;
    }

    public void setFormula(JsonNode formula) {
        this.formula = formula;
    }
}
