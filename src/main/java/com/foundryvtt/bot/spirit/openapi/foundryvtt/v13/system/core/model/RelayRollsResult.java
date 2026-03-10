package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayRollsResult extends AbstractFoundryModel {

    private String clientId;
    private List<RelayRollHistoryEntry> rolls = new ArrayList<RelayRollHistoryEntry>();

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<RelayRollHistoryEntry> getRolls() {
        return this.rolls;
    }

    public void setRolls(List<RelayRollHistoryEntry> rolls) {
        this.rolls = rolls;
    }
}
