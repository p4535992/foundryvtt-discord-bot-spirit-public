package com.foundryvtt.bot.spirit.exception;

/**
 * Runtime exception raised when a downstream relay client call fails.
 */
public class RelayClientException extends IllegalStateException {

    private final String serviceLabel;
    private final String actionName;
    private final int downstreamStatus;

    public RelayClientException(
            String serviceLabel,
            String actionName,
            int downstreamStatus,
            String message,
            Throwable cause) {
        super(message, cause);
        this.serviceLabel = serviceLabel;
        this.actionName = actionName;
        this.downstreamStatus = downstreamStatus;
    }

    public String getServiceLabel() {
        return this.serviceLabel;
    }

    public String getActionName() {
        return this.actionName;
    }

    public int getDownstreamStatus() {
        return this.downstreamStatus;
    }
}
