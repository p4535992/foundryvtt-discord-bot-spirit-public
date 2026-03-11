package com.foundryvtt.bot.spirit.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandErrorResponseEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandResponseEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing.SystemCommandRouterService;

/**
 * HTTP adapter for provider-level system command routing.
 *
 * <p>
 * The flow exposed by this resource is: request JSON -> {@link SystemCommandEnvelope} -> provider
 * router -> core executor or system-specific module -> relay client -> manual Foundry-facing model
 * -> {@link SystemCommandResponseEnvelope}.
 *
 * <p>
 * This resource intentionally does not talk to the generated relay client directly. That boundary
 * stays inside the provider services so the web layer only knows the provider contract.
 */
@Path("/api/foundryvtt/v13/provider/system/command")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Foundry Provider Commands", description = "Provider-level command routing facade over the relay-backed Foundry integration.")
public class FoundrySystemCommandResource {

    private final SystemCommandRouterService systemCommandRouterService;

    @Inject
    public FoundrySystemCommandResource(SystemCommandRouterService systemCommandRouterService) {
        this.systemCommandRouterService = systemCommandRouterService;
    }

    @POST
    @Path("/route")
    @Operation(operationId = "routeFoundrySystemCommand", summary = "Route a provider command", description = "Routes a core or system-specific provider command through the Foundry VTT v13 provider layer.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Command executed successfully.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SystemCommandResponseEnvelope.class))),
            @APIResponse(responseCode = "400", description = "Invalid request envelope or payload.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SystemCommandErrorResponseEnvelope.class))),
            @APIResponse(responseCode = "422", description = "Command could not be routed or executed in the current provider state.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SystemCommandErrorResponseEnvelope.class))),
            @APIResponse(responseCode = "502", description = "Downstream relay call failed.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SystemCommandErrorResponseEnvelope.class))),
            @APIResponse(responseCode = "500", description = "Unexpected internal error.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SystemCommandErrorResponseEnvelope.class)))
    })
    /**
     * Routes a provider command received over HTTP.
     *
     * <p>
     * The incoming envelope is already the public provider contract. The router converts it to the
     * internal {@code SystemCommand}, resolves the target scope ({@code core} or a concrete Foundry
     * system such as {@code dnd5e}), executes the downstream relay call, and wraps the typed result
     * in a response envelope suitable for the upstream caller.
     *
     * @param envelope external provider command envelope
     * @return typed provider response envelope
     */
    public SystemCommandResponseEnvelope route(SystemCommandEnvelope envelope) {
        return this.systemCommandRouterService.routeForResponse(envelope);
    }
}
