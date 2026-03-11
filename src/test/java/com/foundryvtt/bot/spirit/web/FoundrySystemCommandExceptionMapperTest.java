package com.foundryvtt.bot.spirit.web;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.foundryvtt.bot.spirit.exception.RelayClientException;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandErrorResponseEnvelope;

class FoundrySystemCommandExceptionMapperTest {

    private final FoundrySystemCommandExceptionMapper mapper = new FoundrySystemCommandExceptionMapper();

    @Test
    void shouldMapIllegalArgumentExceptionToBadRequestEnvelope() {
        Response response = this.mapper
                .toResponse(new IllegalArgumentException("clientId missing"));

        assertThat(response.getStatus()).isEqualTo(400);
        SystemCommandErrorResponseEnvelope envelope = (SystemCommandErrorResponseEnvelope) response
                .getEntity();
        assertThat(envelope.getErrorCode()).isEqualTo("INVALID_REQUEST");
        assertThat(envelope.getMessage()).isEqualTo("clientId missing");
    }

    @Test
    void shouldMapIllegalStateExceptionToUnprocessableEntityEnvelope() {
        Response response = this.mapper
                .toResponse(new IllegalStateException("Unsupported command"));

        assertThat(response.getStatus()).isEqualTo(422);
        SystemCommandErrorResponseEnvelope envelope = (SystemCommandErrorResponseEnvelope) response
                .getEntity();
        assertThat(envelope.getErrorCode()).isEqualTo("COMMAND_STATE_ERROR");
        assertThat(envelope.getMessage()).isEqualTo("Unsupported command");
    }

    @Test
    void shouldMapRelayClientExceptionToBadGatewayEnvelope() {
        Response response = this.mapper.toResponse(new RelayClientException(
                "Foundry relay",
                "get structure",
                503,
                "Foundry relay call failed while trying to get structure (status=503).",
                new RuntimeException("downstream")));

        assertThat(response.getStatus()).isEqualTo(502);
        SystemCommandErrorResponseEnvelope envelope = (SystemCommandErrorResponseEnvelope) response
                .getEntity();
        assertThat(envelope.getErrorCode()).isEqualTo("RELAY_ERROR");
        assertThat(envelope.getDetails())
                .containsEntry("service", "Foundry relay")
                .containsEntry("action", "get structure")
                .containsEntry("downstreamStatus", 503);
    }
}
