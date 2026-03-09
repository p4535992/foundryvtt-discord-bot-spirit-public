package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eActorDetails extends AbstractDnd5eModel {

    private JsonNode biography;
    private String alignment;
    private String ideal;
    private String bond;
    private String flaw;
    private JsonNode race;
    private JsonNode background;
    private String originalClass;
    private JsonNode xp;
    private String appearance;
    private String trait;
    private String gender;
    private String eyes;
    private String height;
    private String faith;
    private String hair;
    private String skin;
    private String age;
    private String weight;
    private JsonNode type;
    private JsonNode habitat;
    private Double cr;
    private JsonNode treasure;

    public JsonNode getBiography() {
        return this.biography;
    }

    public void setBiography(JsonNode biography) {
        this.biography = biography;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getIdeal() {
        return this.ideal;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }

    public String getBond() {
        return this.bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }

    public String getFlaw() {
        return this.flaw;
    }

    public void setFlaw(String flaw) {
        this.flaw = flaw;
    }

    public JsonNode getRace() {
        return this.race;
    }

    public void setRace(JsonNode race) {
        this.race = race;
    }

    public JsonNode getBackground() {
        return this.background;
    }

    public void setBackground(JsonNode background) {
        this.background = background;
    }

    public String getOriginalClass() {
        return this.originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass;
    }

    public JsonNode getXp() {
        return this.xp;
    }

    public void setXp(JsonNode xp) {
        this.xp = xp;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getTrait() {
        return this.trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyes() {
        return this.eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFaith() {
        return this.faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public String getHair() {
        return this.hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSkin() {
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public JsonNode getType() {
        return this.type;
    }

    public void setType(JsonNode type) {
        this.type = type;
    }

    public JsonNode getHabitat() {
        return this.habitat;
    }

    public void setHabitat(JsonNode habitat) {
        this.habitat = habitat;
    }

    public Double getCr() {
        return this.cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }

    public JsonNode getTreasure() {
        return this.treasure;
    }

    public void setTreasure(JsonNode treasure) {
        this.treasure = treasure;
    }
}
