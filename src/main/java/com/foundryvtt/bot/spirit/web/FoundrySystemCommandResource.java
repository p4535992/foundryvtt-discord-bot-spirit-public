package com.foundryvtt.bot.spirit.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandResponseEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing.SystemCommandRouterService;

/**
 * HTTP adapter for provider-level system command routing.
 */
@Path("/api/foundryvtt/v13/provider/system/command")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoundrySystemCommandResource {

    private final SystemCommandRouterService systemCommandRouterService;

    @Inject
    public FoundrySystemCommandResource(SystemCommandRouterService systemCommandRouterService) {
        this.systemCommandRouterService = systemCommandRouterService;
    }

    @POST
    @Path("/route")
    public SystemCommandResponseEnvelope route(SystemCommandEnvelope envelope) {
        return this.systemCommandRouterService.routeForResponse(envelope);
    }
}
