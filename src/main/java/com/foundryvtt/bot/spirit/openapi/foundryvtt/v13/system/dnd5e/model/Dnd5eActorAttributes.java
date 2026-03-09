package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eActorAttributes extends AbstractDnd5eModel {

    private Dnd5eArmorClass ac;
    private Dnd5eRollConfig init;
    private Dnd5eMovementData movement;
    private JsonNode attunement;
    private Dnd5eSensesData senses;
    private String spellcasting;
    private Integer exhaustion;
    private Dnd5eRollConfig concentration;
    private JsonNode loyalty;
    private JsonNode hd;
    private Dnd5eHitPoints hp;
    private Dnd5eRollConfig death;
    private JsonNode price;
    private JsonNode spell;

    public Dnd5eArmorClass getAc() {
        return this.ac;
    }

    public void setAc(Dnd5eArmorClass ac) {
        this.ac = ac;
    }

    public Dnd5eRollConfig getInit() {
        return this.init;
    }

    public void setInit(Dnd5eRollConfig init) {
        this.init = init;
    }

    public Dnd5eMovementData getMovement() {
        return this.movement;
    }

    public void setMovement(Dnd5eMovementData movement) {
        this.movement = movement;
    }

    public JsonNode getAttunement() {
        return this.attunement;
    }

    public void setAttunement(JsonNode attunement) {
        this.attunement = attunement;
    }

    public Dnd5eSensesData getSenses() {
        return this.senses;
    }

    public void setSenses(Dnd5eSensesData senses) {
        this.senses = senses;
    }

    public String getSpellcasting() {
        return this.spellcasting;
    }

    public void setSpellcasting(String spellcasting) {
        this.spellcasting = spellcasting;
    }

    public Integer getExhaustion() {
        return this.exhaustion;
    }

    public void setExhaustion(Integer exhaustion) {
        this.exhaustion = exhaustion;
    }

    public Dnd5eRollConfig getConcentration() {
        return this.concentration;
    }

    public void setConcentration(Dnd5eRollConfig concentration) {
        this.concentration = concentration;
    }

    public JsonNode getLoyalty() {
        return this.loyalty;
    }

    public void setLoyalty(JsonNode loyalty) {
        this.loyalty = loyalty;
    }

    public JsonNode getHd() {
        return this.hd;
    }

    public void setHd(JsonNode hd) {
        this.hd = hd;
    }

    public Dnd5eHitPoints getHp() {
        return this.hp;
    }

    public void setHp(Dnd5eHitPoints hp) {
        this.hp = hp;
    }

    public Dnd5eRollConfig getDeath() {
        return this.death;
    }

    public void setDeath(Dnd5eRollConfig death) {
        this.death = death;
    }

    public JsonNode getPrice() {
        return this.price;
    }

    public void setPrice(JsonNode price) {
        this.price = price;
    }

    public JsonNode getSpell() {
        return this.spell;
    }

    public void setSpell(JsonNode spell) {
        this.spell = spell;
    }
}
