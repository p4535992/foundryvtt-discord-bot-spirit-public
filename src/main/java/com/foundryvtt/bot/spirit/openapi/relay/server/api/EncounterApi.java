package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import java.math.BigDecimal;
import java.util.Map;

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

@Tag(name = "Encounter")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface EncounterApi {

    /**
     * ## Add to encounter
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param body parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/add-to-encounter")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_addToEncounterPost", summary = "/add-to-encounter", description = "## Add to encounter")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object addToEncounterPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Returns all encounters
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * Response 200: OK
     */
    @GET
    @Path("/encounters")
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_encountersGet", summary = "/encounters", description = "## Returns all encounters")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object encountersGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);


    /**
     * ## End an encounter
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/end-encounter")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_endEncounterPost", summary = "/end-encounter", description = "## End an encounter")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object endEncounterPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Move backward 1 round
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/last-round")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_lastRoundPost", summary = "/last-round", description = "## Move backward 1 round")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object lastRoundPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Move backward 1 turn
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/last-turn")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_lastTurnPost", summary = "/last-turn", description = "## Move backward 1 turn")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object lastTurnPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Move forward 1 round
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/next-round")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_nextRoundPost", summary = "/next-round", description = "## Move forward 1 round")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object nextRoundPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Move forward 1 turn
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/next-turn")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_nextTurnPost", summary = "/next-turn", description = "## Move forward 1 turn")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object nextTurnPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Remove from encounter
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param body parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/remove-from-encounter")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_removeFromEncounterPost", summary = "/remove-from-encounter", description = "## Remove from encounter")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object removeFromEncounterPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Starts an encouter
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param body parameter value.
     * Response 201: Created
     */
    @POST
    @Path("/start-encounter")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Encounter_startEncounterPost", summary = "/start-encounter", description = "## Starts an encouter")
    @APIResponses(value = { 
        @APIResponse(responseCode = "201", description = "Created") })
    
    Object startEncounterPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);

}
