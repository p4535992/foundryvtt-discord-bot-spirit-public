package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eSimpleTrait extends AbstractDnd5eModel {

    private Set<String> value = new LinkedHashSet<String>();
    private String custom;
    private JsonNode communication;
    private JsonNode bonus;
    private JsonNode mastery;

    public Set<String> getValue() {
        return this.value;
    }

    public void setValue(Set<String> value) {
        this.value = value;
    }

    public String getCustom() {
        return this.custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public JsonNode getCommunication() {
        return this.communication;
    }

    public void setCommunication(JsonNode communication) {
        this.communication = communication;
    }

    public JsonNode getBonus() {
        return this.bonus;
    }

    public void setBonus(JsonNode bonus) {
        this.bonus = bonus;
    }

    public JsonNode getMastery() {
        return this.mastery;
    }

    public void setMastery(JsonNode mastery) {
        this.mastery = mastery;
    }
}
