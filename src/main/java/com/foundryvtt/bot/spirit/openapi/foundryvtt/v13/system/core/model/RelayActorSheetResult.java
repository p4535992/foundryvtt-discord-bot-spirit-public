package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayActorSheetResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private String uuid;
    private String html;
    private String css;
    private String error;

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

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCss() {
        return this.css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
