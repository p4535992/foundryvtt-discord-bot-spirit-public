package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayRollDieResult extends AbstractFoundryModel {

    private Integer result;
    private Boolean active;

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
