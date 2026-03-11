package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Typed error envelope for provider command HTTP responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "SystemCommandErrorResponseEnvelope", description = "Provider command error response envelope.")
public class SystemCommandErrorResponseEnvelope {

    @Schema(description = "HTTP status code.", example = "400")
    private final int status;
    @Schema(description = "Stable application error code.", example = "INVALID_REQUEST")
    private final String errorCode;
    @Schema(description = "Human-readable error message.")
    private final String message;
    @Schema(description = "HTTP request path.", example = "/api/foundryvtt/v13/provider/system/command/route")
    private final String path;
    @Schema(description = "Error timestamp.")
    private final OffsetDateTime timestamp;
    @Schema(description = "Additional error details.")
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
