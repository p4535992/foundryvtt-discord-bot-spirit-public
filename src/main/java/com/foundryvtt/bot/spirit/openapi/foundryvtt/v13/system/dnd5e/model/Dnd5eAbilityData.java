package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eAbilityData extends AbstractDnd5eModel {

    private Integer value;
    private Integer proficient;
    private Integer max;
    private Dnd5eRollBonuses bonuses;
    private Dnd5eRollConfig check;
    private Dnd5eRollConfig save;
    private Integer mod;
    private Integer attack;
    private Integer dc;
    private Integer saveBonus;
    private JsonNode checkProf;
    private JsonNode saveProf;

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getProficient() {
        return this.proficient;
    }

    public void setProficient(Integer proficient) {
        this.proficient = proficient;
    }

    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Dnd5eRollBonuses getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(Dnd5eRollBonuses bonuses) {
        this.bonuses = bonuses;
    }

    public Dnd5eRollConfig getCheck() {
        return this.check;
    }

    public void setCheck(Dnd5eRollConfig check) {
        this.check = check;
    }

    public Dnd5eRollConfig getSave() {
        return this.save;
    }

    public void setSave(Dnd5eRollConfig save) {
        this.save = save;
    }

    public Integer getMod() {
        return this.mod;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDc() {
        return this.dc;
    }

    public void setDc(Integer dc) {
        this.dc = dc;
    }

    public Integer getSaveBonus() {
        return this.saveBonus;
    }

    public void setSaveBonus(Integer saveBonus) {
        this.saveBonus = saveBonus;
    }

    public JsonNode getCheckProf() {
        return this.checkProf;
    }

    public void setCheckProf(JsonNode checkProf) {
        this.checkProf = checkProf;
    }

    public JsonNode getSaveProf() {
        return this.saveProf;
    }

    public void setSaveProf(JsonNode saveProf) {
        this.saveProf = saveProf;
    }
}
