package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eItemSystemData extends AbstractDnd5eModel {

    private String identifier;
    private Dnd5eUsesData uses;
    private Map<String, Dnd5eActivityData> activities = new LinkedHashMap<String, Dnd5eActivityData>();
    private List<Dnd5eAdvancementData> advancement = new ArrayList<Dnd5eAdvancementData>();
    private JsonNode description;
    private JsonNode source;

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Dnd5eUsesData getUses() {
        return this.uses;
    }

    public void setUses(Dnd5eUsesData uses) {
        this.uses = uses;
    }

    public Map<String, Dnd5eActivityData> getActivities() {
        return this.activities;
    }

    public void setActivities(Map<String, Dnd5eActivityData> activities) {
        this.activities = activities;
    }

    public List<Dnd5eAdvancementData> getAdvancement() {
        return this.advancement;
    }

    public void setAdvancement(List<Dnd5eAdvancementData> advancement) {
        this.advancement = advancement;
    }

    public JsonNode getDescription() {
        return this.description;
    }

    public void setDescription(JsonNode description) {
        this.description = description;
    }

    public JsonNode getSource() {
        return this.source;
    }

    public void setSource(JsonNode source) {
        this.source = source;
    }
}
