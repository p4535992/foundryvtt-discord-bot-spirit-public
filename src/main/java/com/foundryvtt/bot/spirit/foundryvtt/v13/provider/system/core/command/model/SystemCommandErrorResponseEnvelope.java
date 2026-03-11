package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Typed error envelope for provider command HTTP responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemCommandErrorResponseEnvelope {

    private final int status;
    private final String errorCode;
    private final String message;
    private final String path;
    private final OffsetDateTime timestamp;
    private final Map<String, Object> details;

    public SystemCommandErrorResponseEnvelope(
            int status,
            String errorCode,
            String message,
            String path,
            OffsetDateTime timestamp,
            Map<String, Object> details) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
        if (details == null) {
            this.details = Collections.emptyMap();
        } else {
            this.details = Collections.unmodifiableMap(new HashMap<String, Object>(details));
        }
    }

    public int getStatus() {
        return this.status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    public Map<String, Object> getDetails() {
        return this.details;
    }
}
