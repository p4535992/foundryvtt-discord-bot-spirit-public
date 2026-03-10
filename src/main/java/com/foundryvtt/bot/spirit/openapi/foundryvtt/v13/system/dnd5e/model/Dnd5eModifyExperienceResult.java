package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eModifyExperienceResult extends Dnd5eRelayOperationResult {

    private String actorUuid;
    private Integer amount;
    private Dnd5eActorDocument actor;

    public String getActorUuid() {
        return this.actorUuid;
    }

    public void setActorUuid(String actorUuid) {
        this.actorUuid = actorUuid;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Dnd5eActorDocument getActor() {
        return this.actor;
    }

    public void setActor(Dnd5eActorDocument actor) {
        this.actor = actor;
    }
}
