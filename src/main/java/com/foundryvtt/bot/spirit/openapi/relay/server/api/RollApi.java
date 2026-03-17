package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import com.foundryvtt.bot.spirit.openapi.relay.server.model.RollRequest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

/**
* Represents a collection of functions to interact with the API endpoints.
*/

@Tag(name = "Roll")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface RollApi {

    /**
     * ## Returns the last roll made
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * Response 200: OK
     */
    @GET
    @Path("/lastroll")
    @Produces({ "application/json" })
    @Operation(operationId = "Roll_lastrollGet", summary = "/lastroll", description = "## Returns the last roll made")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object lastrollGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);


    /**
     * ## Makes a new roll in Foundry
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param rollRequest parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/roll")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Roll_rollPost", summary = "/roll", description = "## Makes a new roll in Foundry")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object rollPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "RollRequest") @Valid  RollRequest rollRequest

);


    /**
     * ## Returns up to the last 20 rolls
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param limit (Optional) Max number of rolls to return. Max 20. Default 20.
     * Response 200: OK
     */
    @GET
    @Path("/rolls")
    @Produces({ "application/json" })
    @Operation(operationId = "Roll_rollsGet", summary = "/rolls", description = "## Returns up to the last 20 rolls")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object rollsGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("limit") @Parameter(name = "limit", description = "(Optional) Max number of rolls to return. Max 20. Default 20.")  Integer limit





);

}
