package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayRollExecution extends AbstractFoundryModel {

    private String id;
    private Boolean chatMessageCreated;
    private RelayRollDetails roll;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getChatMessageCreated() {
        return this.chatMessageCreated;
    }

    public void setChatMessageCreated(Boolean chatMessageCreated) {
        this.chatMessageCreated = chatMessageCreated;
    }

    public RelayRollDetails getRoll() {
        return this.roll;
    }

    public void setRoll(RelayRollDetails roll) {
        this.roll = roll;
    }
}
