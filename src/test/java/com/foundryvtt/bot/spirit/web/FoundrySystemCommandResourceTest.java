package com.foundryvtt.bot.spirit.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandResponseEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing.SystemCommandRouterService;

class FoundrySystemCommandResourceTest {

    @Test
    void shouldDelegateTypedEnvelopeToSystemCommandRouterService() {
        SystemCommandRouterService routerService = mock(SystemCommandRouterService.class);
        FoundrySystemCommandResource resource = new FoundrySystemCommandResource(routerService);
        SystemCommandEnvelope envelope = new SystemCommandEnvelope(
                "client-1",
                "api-key",
                "core.getRelayStatus",
                Map.of());
        SystemCommandResponseEnvelope expected = new SystemCommandResponseEnvelope(
                "client-1",
                "core.getRelayStatus",
                null,
                Boolean.TRUE,
                Map.of("status", "ok"),
                Map.of("scope", "core"));

        when(routerService.routeForResponse(envelope)).thenReturn(expected);

        SystemCommandResponseEnvelope result = resource.route(envelope);

        assertThat(result).isEqualTo(expected);
        verify(routerService).routeForResponse(envelope);
    }
}
