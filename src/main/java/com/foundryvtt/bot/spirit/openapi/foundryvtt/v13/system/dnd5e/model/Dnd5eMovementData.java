package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eMovementData extends AbstractDnd5eModel {

    private Double walk;
    private Double burrow;
    private Double climb;
    private Double fly;
    private Double swim;
    private String units;
    private Boolean hover;
    private String special;

    public Double getWalk() {
        return this.walk;
    }

    public void setWalk(Double walk) {
        this.walk = walk;
    }

    public Double getBurrow() {
        return this.burrow;
    }

    public void setBurrow(Double burrow) {
        this.burrow = burrow;
    }

    public Double getClimb() {
        return this.climb;
    }

    public void setClimb(Double climb) {
        this.climb = climb;
    }

    public Double getFly() {
        return this.fly;
    }

    public void setFly(Double fly) {
        this.fly = fly;
    }

    public Double getSwim() {
        return this.swim;
    }

    public void setSwim(Double swim) {
        this.swim = swim;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Boolean getHover() {
        return this.hover;
    }

    public void setHover(Boolean hover) {
        this.hover = hover;
    }

    public String getSpecial() {
        return this.special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
