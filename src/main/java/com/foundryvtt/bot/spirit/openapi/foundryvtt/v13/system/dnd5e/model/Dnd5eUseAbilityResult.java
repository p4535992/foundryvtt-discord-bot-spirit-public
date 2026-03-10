package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eUseAbilityResult extends Dnd5eRelayOperationResult {

    private String actorUuid;
    private String abilityUuid;
    private String abilityName;
    private String targetUuid;
    private String targetName;
    private Dnd5eActorDocument actor;
    private Dnd5eItemDocument ability;

    public String getActorUuid() {
        return this.actorUuid;
    }

    public void setActorUuid(String actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getAbilityUuid() {
        return this.abilityUuid;
    }

    public void setAbilityUuid(String abilityUuid) {
        this.abilityUuid = abilityUuid;
    }

    public String getAbilityName() {
        return this.abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getTargetUuid() {
        return this.targetUuid;
    }

    public void setTargetUuid(String targetUuid) {
        this.targetUuid = targetUuid;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Dnd5eActorDocument getActor() {
        return this.actor;
    }

    public void setActor(Dnd5eActorDocument actor) {
        this.actor = actor;
    }

    public Dnd5eItemDocument getAbility() {
        return this.ability;
    }

    public void setAbility(Dnd5eItemDocument ability) {
        this.ability = ability;
    }
}
