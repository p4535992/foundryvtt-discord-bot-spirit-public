package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eActorSystemData extends AbstractDnd5eModel {

    private Map<String, Dnd5eAbilityData> abilities = new LinkedHashMap<String, Dnd5eAbilityData>();
    private Dnd5eActorAttributes attributes;
    private Dnd5eActorBonuses bonuses;
    private Dnd5eActorDetails details;
    private Map<String, Dnd5eRollConfig> skills = new LinkedHashMap<String, Dnd5eRollConfig>();
    private Map<String, Dnd5eRollConfig> tools = new LinkedHashMap<String, Dnd5eRollConfig>();
    private Map<String, Dnd5eSpellSlotData> spells = new LinkedHashMap<String, Dnd5eSpellSlotData>();
    private Dnd5eActorTraits traits;
    private JsonNode currency;
    private JsonNode resources;
    private JsonNode favorites;
    private JsonNode bastion;
    private JsonNode source;

    public Map<String, Dnd5eAbilityData> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(Map<String, Dnd5eAbilityData> abilities) {
        this.abilities = abilities;
    }

    public Dnd5eActorAttributes getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Dnd5eActorAttributes attributes) {
        this.attributes = attributes;
    }

    public Dnd5eActorBonuses getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(Dnd5eActorBonuses bonuses) {
        this.bonuses = bonuses;
    }

    public Dnd5eActorDetails getDetails() {
        return this.details;
    }

    public void setDetails(Dnd5eActorDetails details) {
        this.details = details;
    }

    public Map<String, Dnd5eRollConfig> getSkills() {
        return this.skills;
    }

    public void setSkills(Map<String, Dnd5eRollConfig> skills) {
        this.skills = skills;
    }

    public Map<String, Dnd5eRollConfig> getTools() {
        return this.tools;
    }

    public void setTools(Map<String, Dnd5eRollConfig> tools) {
        this.tools = tools;
    }

    public Map<String, Dnd5eSpellSlotData> getSpells() {
        return this.spells;
    }

    public void setSpells(Map<String, Dnd5eSpellSlotData> spells) {
        this.spells = spells;
    }

    public Dnd5eActorTraits getTraits() {
        return this.traits;
    }

    public void setTraits(Dnd5eActorTraits traits) {
        this.traits = traits;
    }

    public JsonNode getCurrency() {
        return this.currency;
    }

    public void setCurrency(JsonNode currency) {
        this.currency = currency;
    }

    public JsonNode getResources() {
        return this.resources;
    }

    public void setResources(JsonNode resources) {
        this.resources = resources;
    }

    public JsonNode getFavorites() {
        return this.favorites;
    }

    public void setFavorites(JsonNode favorites) {
        this.favorites = favorites;
    }

    public JsonNode getBastion() {
        return this.bastion;
    }

    public void setBastion(JsonNode bastion) {
        this.bastion = bastion;
    }

    public JsonNode getSource() {
        return this.source;
    }

    public void setSource(JsonNode source) {
        this.source = source;
    }
}
