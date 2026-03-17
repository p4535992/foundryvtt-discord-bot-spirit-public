package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Represents a collection of functions to interact with the API endpoints.
 */

@Tag(name = "default")

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface DefaultApi {

    /**
     * Returns the API status
     *
     * Response 200: OK
     */
    @GET
    @Path("/api/status")
    @Produces({
            "application/json" })
    @Operation(operationId = "default_apiStatusGet", summary = "/api/status", description = "Returns the API status")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object apiStatusGet();

    /**
     * Returns connected client Foundry Worlds
     *
     * @param xApiKey parameter value. Response 200: OK
     */
    @GET
    @Path("/clients")
    @Produces({
            "application/json" })
    @Operation(operationId = "default_clientsGet", summary = "/clients", description = "Returns connected client Foundry Worlds")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object clientsGet(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

    );

}
