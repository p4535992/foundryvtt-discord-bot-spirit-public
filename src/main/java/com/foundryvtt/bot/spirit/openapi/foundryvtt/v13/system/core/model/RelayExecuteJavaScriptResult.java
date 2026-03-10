package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import com.fasterxml.jackson.databind.JsonNode;

public class RelayExecuteJavaScriptResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private Boolean success;
    private JsonNode result;
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

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public JsonNode getResult() {
        return this.result;
    }

    public void setResult(JsonNode result) {
        this.result = result;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
