package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dnd5eSpellItemSystemData extends Dnd5eItemSystemData {

    private String ability;
    private Dnd5eActivationData activation;
    private Dnd5eDurationData duration;
    private Integer level;
    private Dnd5eSpellMaterialsData materials;
    private String method;
    private Integer prepared;
    private Set<String> properties = new LinkedHashSet<String>();
    private Dnd5eRangeData range;
    private String school;
    private String sourceClass;
    private Dnd5eTargetData target;

    public String getAbility() {
        return this.ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Dnd5eActivationData getActivation() {
        return this.activation;
    }

    public void setActivation(Dnd5eActivationData activation) {
        this.activation = activation;
    }

    public Dnd5eDurationData getDuration() {
        return this.duration;
    }

    public void setDuration(Dnd5eDurationData duration) {
        this.duration = duration;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Dnd5eSpellMaterialsData getMaterials() {
        return this.materials;
    }

    public void setMaterials(Dnd5eSpellMaterialsData materials) {
        this.materials = materials;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getPrepared() {
        return this.prepared;
    }

    public void setPrepared(Integer prepared) {
        this.prepared = prepared;
    }

    public Set<String> getProperties() {
        return this.properties;
    }

    public void setProperties(Set<String> properties) {
        this.properties = properties;
    }

    public Dnd5eRangeData getRange() {
        return this.range;
    }

    public void setRange(Dnd5eRangeData range) {
        this.range = range;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSourceClass() {
        return this.sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public Dnd5eTargetData getTarget() {
        return this.target;
    }

    public void setTarget(Dnd5eTargetData target) {
        this.target = target;
    }
}
