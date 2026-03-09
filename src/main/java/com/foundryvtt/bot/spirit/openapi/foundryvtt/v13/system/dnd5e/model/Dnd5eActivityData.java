package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Dnd5eActivityData extends AbstractDnd5eModel {

    @JsonProperty("_id")
    private String id;
    private String type;
    private String name;
    private String img;
    private Integer sort;
    private Dnd5eActivationData activation;
    private JsonNode consumption;
    private JsonNode description;
    private Dnd5eDurationData duration;
    private List<JsonNode> effects = new ArrayList<JsonNode>();
    private JsonNode flags;
    private Dnd5eRangeData range;
    private Dnd5eTargetData target;
    private Dnd5eUsesData uses;
    private JsonNode visibility;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Dnd5eActivationData getActivation() {
        return this.activation;
    }

    public void setActivation(Dnd5eActivationData activation) {
        this.activation = activation;
    }

    public JsonNode getConsumption() {
        return this.consumption;
    }

    public void setConsumption(JsonNode consumption) {
        this.consumption = consumption;
    }

    public JsonNode getDescription() {
        return this.description;
    }

    public void setDescription(JsonNode description) {
        this.description = description;
    }

    public Dnd5eDurationData getDuration() {
        return this.duration;
    }

    public void setDuration(Dnd5eDurationData duration) {
        this.duration = duration;
    }

    public List<JsonNode> getEffects() {
        return this.effects;
    }

    public void setEffects(List<JsonNode> effects) {
        this.effects = effects;
    }

    public JsonNode getFlags() {
        return this.flags;
    }

    public void setFlags(JsonNode flags) {
        this.flags = flags;
    }

    public Dnd5eRangeData getRange() {
        return this.range;
    }

    public void setRange(Dnd5eRangeData range) {
        this.range = range;
    }

    public Dnd5eTargetData getTarget() {
        return this.target;
    }

    public void setTarget(Dnd5eTargetData target) {
        this.target = target;
    }

    public Dnd5eUsesData getUses() {
        return this.uses;
    }

    public void setUses(Dnd5eUsesData uses) {
        this.uses = uses;
    }

    public JsonNode getVisibility() {
        return this.visibility;
    }

    public void setVisibility(JsonNode visibility) {
        this.visibility = visibility;
    }
}
