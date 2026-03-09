package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eActorBonuses extends AbstractDnd5eModel {

    private Dnd5eAttackBonuses mwak;
    private Dnd5eAttackBonuses rwak;
    private Dnd5eAttackBonuses msak;
    private Dnd5eAttackBonuses rsak;
    private Dnd5eRollBonuses abilities;
    private Dnd5eRollBonuses spell;

    public Dnd5eAttackBonuses getMwak() {
        return this.mwak;
    }

    public void setMwak(Dnd5eAttackBonuses mwak) {
        this.mwak = mwak;
    }

    public Dnd5eAttackBonuses getRwak() {
        return this.rwak;
    }

    public void setRwak(Dnd5eAttackBonuses rwak) {
        this.rwak = rwak;
    }

    public Dnd5eAttackBonuses getMsak() {
        return this.msak;
    }

    public void setMsak(Dnd5eAttackBonuses msak) {
        this.msak = msak;
    }

    public Dnd5eAttackBonuses getRsak() {
        return this.rsak;
    }

    public void setRsak(Dnd5eAttackBonuses rsak) {
        this.rsak = rsak;
    }

    public Dnd5eRollBonuses getAbilities() {
        return this.abilities;
    }

    public void setAbilities(Dnd5eRollBonuses abilities) {
        this.abilities = abilities;
    }

    public Dnd5eRollBonuses getSpell() {
        return this.spell;
    }

    public void setSpell(Dnd5eRollBonuses spell) {
        this.spell = spell;
    }
}
