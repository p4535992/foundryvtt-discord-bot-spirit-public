package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelaySessionOperationResult extends AbstractFoundryModel {

    private Boolean success;
    private String message;
    private String error;
    private String details;
    private String sessionId;
    private String clientId;
    private Boolean existingSession;
    private String expectedClientId;
    private String handshakeInstance;

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getExistingSession() {
        return this.existingSession;
    }

    public void setExistingSession(Boolean existingSession) {
        this.existingSession = existingSession;
    }

    public String getExpectedClientId() {
        return this.expectedClientId;
    }

    public void setExpectedClientId(String expectedClientId) {
        this.expectedClientId = expectedClientId;
    }

    public String getHandshakeInstance() {
        return this.handshakeInstance;
    }

    public void setHandshakeInstance(String handshakeInstance) {
        this.handshakeInstance = handshakeInstance;
    }
}
