package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eHitPoints extends AbstractDnd5eModel {

    private Integer dt;
    private Integer max;
    private Integer temp;
    private Integer tempmax;
    private Integer value;
    private String formula;
    private Dnd5eRollBonuses bonuses;

    public Integer getDt() {
        return this.dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getTemp() {
        return this.temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getTempmax() {
        return this.tempmax;
    }

    public void setTempmax(Integer tempmax) {
        this.tempmax = tempmax;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Dnd5eRollBonuses getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(Dnd5eRollBonuses bonuses) {
        this.bonuses = bonuses;
    }
}
