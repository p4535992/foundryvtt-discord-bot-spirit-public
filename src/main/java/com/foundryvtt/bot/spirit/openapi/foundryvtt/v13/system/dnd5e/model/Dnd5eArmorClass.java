package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eArmorClass extends AbstractDnd5eModel {

    private String calc;
    private Integer flat;
    private String formula;
    private Integer value;
    private Integer armor;
    private Integer shield;
    private Integer cover;
    private Integer dex;
    private Integer base;
    private JsonNode min;
    private String bonus;
    private JsonNode equippedArmor;
    private JsonNode equippedShield;

    public String getCalc() {
        return this.calc;
    }

    public void setCalc(String calc) {
        this.calc = calc;
    }

    public Integer getFlat() {
        return this.flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getArmor() {
        return this.armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getShield() {
        return this.shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    public Integer getCover() {
        return this.cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public Integer getDex() {
        return this.dex;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public Integer getBase() {
        return this.base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public JsonNode getMin() {
        return this.min;
    }

    public void setMin(JsonNode min) {
        this.min = min;
    }

    public String getBonus() {
        return this.bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public JsonNode getEquippedArmor() {
        return this.equippedArmor;
    }

    public void setEquippedArmor(JsonNode equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public JsonNode getEquippedShield() {
        return this.equippedShield;
    }

    public void setEquippedShield(JsonNode equippedShield) {
        this.equippedShield = equippedShield;
    }
}
