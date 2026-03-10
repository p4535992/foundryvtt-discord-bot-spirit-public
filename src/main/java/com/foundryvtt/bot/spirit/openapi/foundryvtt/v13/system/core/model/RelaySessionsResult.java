package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelaySessionsResult extends AbstractFoundryModel {

    private List<RelayActiveSessionInfo> activeSessions = new ArrayList<RelayActiveSessionInfo>();
    private List<RelayPendingSessionInfo> pendingSessions = new ArrayList<RelayPendingSessionInfo>();

    public List<RelayActiveSessionInfo> getActiveSessions() {
        return this.activeSessions;
    }

    public void setActiveSessions(List<RelayActiveSessionInfo> activeSessions) {
        this.activeSessions = activeSessions;
    }

    public List<RelayPendingSessionInfo> getPendingSessions() {
        return this.pendingSessions;
    }

    public void setPendingSessions(List<RelayPendingSessionInfo> pendingSessions) {
        this.pendingSessions = pendingSessions;
    }
}
