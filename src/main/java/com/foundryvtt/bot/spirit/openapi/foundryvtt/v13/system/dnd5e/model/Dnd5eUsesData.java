package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eUsesData extends AbstractDnd5eModel {

    private Integer spent;
    private JsonNode max;
    private Integer value;
    private List<Dnd5eUsesRecoveryData> recovery = new ArrayList<Dnd5eUsesRecoveryData>();

    public Integer getSpent() {
        return this.spent;
    }

    public void setSpent(Integer spent) {
        this.spent = spent;
    }

    public JsonNode getMax() {
        return this.max;
    }

    public void setMax(JsonNode max) {
        this.max = max;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Dnd5eUsesRecoveryData> getRecovery() {
        return this.recovery;
    }

    public void setRecovery(List<Dnd5eUsesRecoveryData> recovery) {
        this.recovery = recovery;
    }
}
