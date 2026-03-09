package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eActorTraits extends AbstractDnd5eModel {

    private String size;
    private Dnd5eDamageTrait di;
    private Dnd5eDamageTrait dr;
    private Dnd5eDamageTrait dv;
    private JsonNode dm;
    private Dnd5eSimpleTrait ci;
    private Dnd5eSimpleTrait languages;
    private Dnd5eSimpleTrait weaponProf;
    private Dnd5eSimpleTrait armorProf;
    private Boolean important;

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Dnd5eDamageTrait getDi() {
        return this.di;
    }

    public void setDi(Dnd5eDamageTrait di) {
        this.di = di;
    }

    public Dnd5eDamageTrait getDr() {
        return this.dr;
    }

    public void setDr(Dnd5eDamageTrait dr) {
        this.dr = dr;
    }

    public Dnd5eDamageTrait getDv() {
        return this.dv;
    }

    public void setDv(Dnd5eDamageTrait dv) {
        this.dv = dv;
    }

    public JsonNode getDm() {
        return this.dm;
    }

    public void setDm(JsonNode dm) {
        this.dm = dm;
    }

    public Dnd5eSimpleTrait getCi() {
        return this.ci;
    }

    public void setCi(Dnd5eSimpleTrait ci) {
        this.ci = ci;
    }

    public Dnd5eSimpleTrait getLanguages() {
        return this.languages;
    }

    public void setLanguages(Dnd5eSimpleTrait languages) {
        this.languages = languages;
    }

    public Dnd5eSimpleTrait getWeaponProf() {
        return this.weaponProf;
    }

    public void setWeaponProf(Dnd5eSimpleTrait weaponProf) {
        this.weaponProf = weaponProf;
    }

    public Dnd5eSimpleTrait getArmorProf() {
        return this.armorProf;
    }

    public void setArmorProf(Dnd5eSimpleTrait armorProf) {
        this.armorProf = armorProf;
    }

    public Boolean getImportant() {
        return this.important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }
}
