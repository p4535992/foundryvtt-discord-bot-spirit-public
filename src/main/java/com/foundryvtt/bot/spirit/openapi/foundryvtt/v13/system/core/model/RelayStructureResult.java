package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayStructureResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private List<RelayStructureNode> folders = new ArrayList<RelayStructureNode>();

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

    public List<RelayStructureNode> getFolders() {
        return this.folders;
    }

    public void setFolders(List<RelayStructureNode> folders) {
        this.folders = folders;
    }
}
