package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayLastRollResult extends AbstractFoundryModel {

    private String clientId;
    private RelayRollHistoryEntry roll;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public RelayRollHistoryEntry getRoll() {
        return this.roll;
    }

    public void setRoll(RelayRollHistoryEntry roll) {
        this.roll = roll;
    }
}
