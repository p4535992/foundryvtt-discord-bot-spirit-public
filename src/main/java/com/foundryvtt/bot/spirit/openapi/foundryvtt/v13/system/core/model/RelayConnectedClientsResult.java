package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayConnectedClientsResult extends AbstractFoundryModel {

    private Integer total;
    private List<RelayClientInfo> clients = new ArrayList<RelayClientInfo>();

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RelayClientInfo> getClients() {
        return this.clients;
    }

    public void setClients(List<RelayClientInfo> clients) {
        this.clients = clients;
    }
}
