package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayRollResult extends AbstractFoundryModel {

    private String clientId;
    private Boolean success;
    private RelayRollExecution roll;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public RelayRollExecution getRoll() {
        return this.roll;
    }

    public void setRoll(RelayRollExecution roll) {
        this.roll = roll;
    }
}
