package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eSensesData extends AbstractDnd5eModel {

    private Double darkvision;
    private Double blindsight;
    private Double tremorsense;
    private Double truesight;
    private String special;
    private String units;
    private Integer passive;

    public Double getDarkvision() {
        return this.darkvision;
    }

    public void setDarkvision(Double darkvision) {
        this.darkvision = darkvision;
    }

    public Double getBlindsight() {
        return this.blindsight;
    }

    public void setBlindsight(Double blindsight) {
        this.blindsight = blindsight;
    }

    public Double getTremorsense() {
        return this.tremorsense;
    }

    public void setTremorsense(Double tremorsense) {
        this.tremorsense = tremorsense;
    }

    public Double getTruesight() {
        return this.truesight;
    }

    public void setTruesight(Double truesight) {
        this.truesight = truesight;
    }

    public String getSpecial() {
        return this.special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getPassive() {
        return this.passive;
    }

    public void setPassive(Integer passive) {
        this.passive = passive;
    }
}
