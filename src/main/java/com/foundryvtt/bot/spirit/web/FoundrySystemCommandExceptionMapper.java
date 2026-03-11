package com.foundryvtt.bot.spirit.web;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.foundryvtt.bot.spirit.exception.RelayClientException;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandErrorResponseEnvelope;

/**
 * Maps provider command exceptions to a stable JSON error envelope.
 */
@Provider
public class FoundrySystemCommandExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof RelayClientException relayClientException) {
            return this.buildResponse(
                    Response.Status.BAD_GATEWAY,
                    "RELAY_ERROR",
                    relayClientException.getMessage(),
                    Map.of(
                            "service", relayClientException.getServiceLabel(),
                            "action", relayClientException.getActionName(),
                            "downstreamStatus", relayClientException.getDownstreamStatus()));
        }
        if (exception instanceof IllegalArgumentException illegalArgumentException) {
            return this.buildResponse(
                    Response.Status.BAD_REQUEST,
                    "INVALID_REQUEST",
                    illegalArgumentException.getMessage(),
                    null);
        }
        if (exception instanceof IllegalStateException illegalStateException) {
            return this.buildResponse(
                    422,
                    "COMMAND_STATE_ERROR",
                    illegalStateException.getMessage(),
                    null);
        }
        return this.buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "Unexpected error while handling provider command.",
                null);
    }

    private Response buildResponse(
            Response.Status status,
            String errorCode,
            String message,
            Map<String, Object> details) {
        return this.buildResponse(status.getStatusCode(), errorCode, message, details);
    }

    private Response buildResponse(
            int status,
            String errorCode,
            String message,
            Map<String, Object> details) {
        Map<String, Object> payloadDetails = new HashMap<String, Object>();
        if (details != null) {
            payloadDetails.putAll(details);
        }
        SystemCommandErrorResponseEnvelope envelope = new SystemCommandErrorResponseEnvelope(
                status,
                errorCode,
                message,
                this.pathOrNull(),
                OffsetDateTime.now(),
                payloadDetails);
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(envelope)
                .build();
    }

    private String pathOrNull() {
        if (this.uriInfo == null || this.uriInfo.getRequestUri() == null) {
            return null;
        }
        return this.uriInfo.getRequestUri().getPath();
    }
}
