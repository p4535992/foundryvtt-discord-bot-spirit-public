package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eRollBonuses extends AbstractDnd5eModel {

    private String check;
    private String save;
    private String skill;
    private String passive;
    private String attack;
    private String damage;
    private String dc;
    private String level;
    private String overall;

    public String getCheck() {
        return this.check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getSave() {
        return this.save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPassive() {
        return this.passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getAttack() {
        return this.attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDamage() {
        return this.damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDc() {
        return this.dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOverall() {
        return this.overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }
}
