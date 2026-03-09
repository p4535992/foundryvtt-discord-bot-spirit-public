package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eDurationData extends AbstractDnd5eModel {

    private JsonNode value;
    private String units;
    private String special;
    private Boolean concentration;
    private Boolean override;

    public JsonNode getValue() {
        return this.value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSpecial() {
        return this.special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public Boolean getConcentration() {
        return this.concentration;
    }

    public void setConcentration(Boolean concentration) {
        this.concentration = concentration;
    }

    public Boolean getOverride() {
        return this.override;
    }

    public void setOverride(Boolean override) {
        this.override = override;
    }
}
