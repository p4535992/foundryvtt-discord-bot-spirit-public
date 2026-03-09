package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eAdvancementData extends AbstractDnd5eModel {

    @JsonProperty("_id")
    private String id;
    private String type;
    private JsonNode configuration;
    private JsonNode value;
    private Integer level;
    private String title;
    private String hint;
    private String icon;
    private String classRestriction;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(JsonNode configuration) {
        this.configuration = configuration;
    }

    public JsonNode getValue() {
        return this.value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClassRestriction() {
        return this.classRestriction;
    }

    public void setClassRestriction(String classRestriction) {
        this.classRestriction = classRestriction;
    }
}
