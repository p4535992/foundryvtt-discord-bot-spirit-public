package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eWeaponItemSystemData extends Dnd5eItemSystemData {

    private JsonNode ammunition;
    private JsonNode armor;
    private JsonNode damage;
    private Integer magicalBonus;
    private String mastery;
    private Set<String> properties = new LinkedHashSet<String>();
    private Integer proficient;
    private JsonNode range;
    private JsonNode type;

    public JsonNode getAmmunition() {
        return this.ammunition;
    }

    public void setAmmunition(JsonNode ammunition) {
        this.ammunition = ammunition;
    }

    public JsonNode getArmor() {
        return this.armor;
    }

    public void setArmor(JsonNode armor) {
        this.armor = armor;
    }

    public JsonNode getDamage() {
        return this.damage;
    }

    public void setDamage(JsonNode damage) {
        this.damage = damage;
    }

    public Integer getMagicalBonus() {
        return this.magicalBonus;
    }

    public void setMagicalBonus(Integer magicalBonus) {
        this.magicalBonus = magicalBonus;
    }

    public String getMastery() {
        return this.mastery;
    }

    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    public Set<String> getProperties() {
        return this.properties;
    }

    public void setProperties(Set<String> properties) {
        this.properties = properties;
    }

    public Integer getProficient() {
        return this.proficient;
    }

    public void setProficient(Integer proficient) {
        this.proficient = proficient;
    }

    public JsonNode getRange() {
        return this.range;
    }

    public void setRange(JsonNode range) {
        this.range = range;
    }

    public JsonNode getType() {
        return this.type;
    }

    public void setType(JsonNode type) {
        this.type = type;
    }
}
