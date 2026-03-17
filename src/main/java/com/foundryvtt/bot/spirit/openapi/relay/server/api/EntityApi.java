package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import java.math.BigDecimal;
import com.foundryvtt.bot.spirit.openapi.relay.server.model.CreatePostRequest;
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

@Tag(name = "Entity")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface EntityApi {

    /**
     * ## Creates a new entity in Foundry with the given JSON
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param createPostRequest parameter value.
     * Response 201: Created
     */
    @POST
    @Path("/create")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_createPost", summary = "/create", description = "## Creates a new entity in Foundry with the given JSON")
    @APIResponses(value = { 
        @APIResponse(responseCode = "201", description = "Created") })
    
    Object createPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "CreatePostRequest") @Valid  CreatePostRequest createPostRequest

);


    /**
     * ## Decrease an attribute
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param selected parameter value.
     * @param body parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/decrease")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_decreasePost", summary = "/decrease", description = "## Decrease an attribute")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object decreasePost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("selected") @Parameter(name = "selected")  Boolean selected





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Deletes an entity from Foundry
     *
     * @param xApiKey parameter value.
     * @param clientId parameter value.
     * @param selected parameter value.
     * Response 200: OK
     */
    @DELETE
    @Path("/delete")
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_deleteDelete", summary = "/delete", description = "## Deletes an entity from Foundry")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object deleteDelete(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("selected") @Parameter(name = "selected")  Boolean selected





);


    /**
     * ## Returns JSON data for entity
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param selected Use selected entity
     * @param actor Use selected entity&#39;s actor
     * Response 200: OK
     */
    @GET
    @Path("/get")
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_getGet", summary = "/get", description = "## Returns JSON data for entity")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object getGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("selected") @Parameter(name = "selected", description = "Use selected entity")  Boolean selected





,@QueryParam("actor") @Parameter(name = "actor", description = "Use selected entity's actor")  Boolean actor





);


    /**
     * ## Give an item to an actor  - Item uuid required  - Selected actor or actor uuid  - Optionally take from another actor  - Optionally specify quantity
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param body parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/give")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_givePost", summary = "/remove", description = "## Give an item to an actor  - Item uuid required  - Selected actor or actor uuid  - Optionally take from another actor  - Optionally specify quantity")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object givePost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Increase an attribute
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param uuid parameter value.
     * @param body parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/increase")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_increasePost", summary = "/increase", description = "## Increase an attribute")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object increasePost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("uuid") @Parameter(name = "uuid")  String uuid





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Reduce to 0hp and mark dead and defeated
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param selected parameter value.
     * @param requestBody parameter value.
     * Response 200: OK
     */
    @POST
    @Path("/kill")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_killPost", summary = "/kill", description = "## Reduce to 0hp and mark dead and defeated")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object killPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("selected") @Parameter(name = "selected")  Boolean selected





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * ## Updates and entity with the given JSON props
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param uuid parameter value.
     * @param body parameter value.
     * Response 200: OK
     */
    @PUT
    @Path("/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "Entity_updatePut", summary = "/update", description = "## Updates and entity with the given JSON props")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object updatePut(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("uuid") @Parameter(name = "uuid")  String uuid





,



@Parameter(name = "body") @Valid  Object body

);

}
