package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayEncounterResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private List<RelayEncounter> encounters = new ArrayList<RelayEncounter>();

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<RelayEncounter> getEncounters() {
        return this.encounters;
    }

    public void setEncounters(List<RelayEncounter> encounters) {
        this.encounters = encounters;
    }
}
