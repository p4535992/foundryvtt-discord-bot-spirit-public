package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eAttackBonuses extends AbstractDnd5eModel {

    private String attack;
    private String damage;

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
}
