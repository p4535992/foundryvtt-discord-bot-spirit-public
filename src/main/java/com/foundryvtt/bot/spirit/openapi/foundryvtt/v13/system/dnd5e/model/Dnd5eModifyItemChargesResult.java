package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

public class Dnd5eModifyItemChargesResult extends Dnd5eRelayOperationResult {

    private String actorUuid;
    private String itemUuid;
    private String itemName;
    private String amount;
    private Dnd5eActorDocument actor;
    private Dnd5eItemDocument item;

    public String getActorUuid() {
        return this.actorUuid;
    }

    public void setActorUuid(String actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getItemUuid() {
        return this.itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Dnd5eActorDocument getActor() {
        return this.actor;
    }

    public void setActor(Dnd5eActorDocument actor) {
        this.actor = actor;
    }

    public Dnd5eItemDocument getItem() {
        return this.item;
    }

    public void setItem(Dnd5eItemDocument item) {
        this.item = item;
    }
}
