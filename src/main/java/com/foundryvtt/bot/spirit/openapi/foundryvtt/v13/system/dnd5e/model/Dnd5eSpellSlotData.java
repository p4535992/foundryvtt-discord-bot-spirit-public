package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eSpellSlotData extends AbstractDnd5eModel {

    private Integer value;
    private Integer override;

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getOverride() {
        return this.override;
    }

    public void setOverride(Integer override) {
        this.override = override;
    }
}
