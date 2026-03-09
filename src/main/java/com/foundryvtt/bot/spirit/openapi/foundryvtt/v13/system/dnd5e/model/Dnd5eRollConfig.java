package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eRollConfig extends AbstractDnd5eModel {

    private Double value;
    private String ability;
    private String bonus;
    private Integer success;
    private Integer failure;
    private Integer limit;
    private Dnd5eRollBonuses bonuses;
    private JsonNode roll;
    private JsonNode prof;

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getAbility() {
        return this.ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getBonus() {
        return this.bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Integer getSuccess() {
        return this.success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return this.failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Dnd5eRollBonuses getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(Dnd5eRollBonuses bonuses) {
        this.bonuses = bonuses;
    }

    public JsonNode getRoll() {
        return this.roll;
    }

    public void setRoll(JsonNode roll) {
        this.roll = roll;
    }

    public JsonNode getProf() {
        return this.prof;
    }

    public void setProf(JsonNode prof) {
        this.prof = prof;
    }
}
