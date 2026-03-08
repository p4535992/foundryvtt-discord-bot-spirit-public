package com.foundryvtt.bot.spirit.openapi.relay.system.api;

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

@Tag(name = "Session")

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface SessionApi {

    /**
     * Ends a headless Foundry session.
     *
     * @param xApiKey 
     * @param sessionId The session to end
     * @return OK
     */
    @DELETE
    @Path("/end-session")
    @Produces({ "application/json" })
    
    @Operation(operationId = "Session_endSessionDelete", summary = "/end-session", description = "Ends a headless Foundry session.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object endSessionDelete(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("sessionId") @Parameter(name = "sessionId", description = "The session to end")  String sessionId





);


    /**
     * Gets the currently active headless Foundry session.
     *
     * @param xApiKey 
     * @return OK
     */
    @GET
    @Path("/session")
    @Produces({ "application/json" })
    
    @Operation(operationId = "Session_sessionGet", summary = "/session", description = "Gets the currently active headless Foundry session.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object sessionGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


);


    /**
     * Creates a temporary, one-time-use, token that can be used to create a headless Foundry session.
     *
     * @param xApiKey 
     * @param xFoundryUrl The url to your foundry game
     * @param xUsername The username to log in with (eg. \&quot;Gamemaster\&quot;)
     * @param xPassword The password to log in with
     * @param xWorldName (Optional) The name of the world as it appears in foundry if the world is not already loaded.
     * @param requestBody 
     * @return OK
     */
    @POST
    @Path("/session-handshake")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "Session_sessionHandshakePost", summary = "/session-handshake", description = "Creates a temporary, one-time-use, token that can be used to create a headless Foundry session.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object sessionHandshakePost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,


@HeaderParam("x-foundry-url") @Parameter(name = "x-foundry-url", description = "The url to your foundry game")  String xFoundryUrl


,


@HeaderParam("x-username") @Parameter(name = "x-username", description = "The username to log in with (eg. \"Gamemaster\")")  String xUsername


,


@HeaderParam("x-password") @Parameter(name = "x-password", description = "The password to log in with")  String xPassword


,


@HeaderParam("x-world-name") @Parameter(name = "x-world-name", description = "(Optional) The name of the world as it appears in foundry if the world is not already loaded.")  String xWorldName


,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * Starts a headless Foundry session. Must provide a handshake token and the encrypted password.
     *
     * @param xApiKey 
     * @param body 
     * @return OK
     */
    @POST
    @Path("/start-session")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "Session_startSessionPost", summary = "/start-session", description = "Starts a headless Foundry session. Must provide a handshake token and the encrypted password.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object startSessionPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,



@Parameter(name = "body") @Valid  Object body

);

}
