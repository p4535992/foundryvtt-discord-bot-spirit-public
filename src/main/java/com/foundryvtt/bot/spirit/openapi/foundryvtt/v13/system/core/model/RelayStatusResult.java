package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayStatusResult extends AbstractFoundryModel {

    private String status;
    private String version;
    private String websocket;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWebsocket() {
        return this.websocket;
    }

    public void setWebsocket(String websocket) {
        this.websocket = websocket;
    }
}
