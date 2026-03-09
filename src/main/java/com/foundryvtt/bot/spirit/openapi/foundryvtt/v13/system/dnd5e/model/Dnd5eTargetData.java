package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eTargetData extends AbstractDnd5eModel {

    private JsonNode template;
    private JsonNode affects;
    private Boolean override;
    private Boolean prompt;

    public JsonNode getTemplate() {
        return this.template;
    }

    public void setTemplate(JsonNode template) {
        this.template = template;
    }

    public JsonNode getAffects() {
        return this.affects;
    }

    public void setAffects(JsonNode affects) {
        this.affects = affects;
    }

    public Boolean getOverride() {
        return this.override;
    }

    public void setOverride(Boolean override) {
        this.override = override;
    }

    public Boolean getPrompt() {
        return this.prompt;
    }

    public void setPrompt(Boolean prompt) {
        this.prompt = prompt;
    }
}
