package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import jakarta.validation.Valid;
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

@Tag(name = "Utilities")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface UtilitiesApi {

    /**
     * #### Executes Javascript in Foundry Accepts the script as a file upload, or as a raw string
     * in the body with the key \&quot;script\&quot;. If included as a raw string in the body excape
     * quotes and backslashes, and remove comments. Returns the result of the code execution.
     *
     * @param xApiKey  parameter value.
     * @param clientId parameter value.
     * @param body     parameter value. Response 200: OK
     */
    @POST
    @Path("/execute-js")
    @Consumes({
            "application/json" })
    @Produces({
            "application/json" })
    @Operation(operationId = "Utilities_executeJsPost", summary = "/execute-js", description = "#### Executes Javascript in Foundry  Accepts the script as a file upload, or as a raw string in the body with the key \"script\".  If included as a raw string in the body excape quotes and backslashes, and remove comments.  Returns the result of the code execution.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object executeJsPost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            ,

            @Parameter(name = "body") @Valid Object body

    );

    /**
     * **Selects entities in Foundry**
     *
     * @param xApiKey  parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param body     parameter value. Response 200: OK
     */
    @POST
    @Path("/select")
    @Consumes({
            "application/json" })
    @Produces({
            "application/json" })
    @Operation(operationId = "Utilities_selectPost", summary = "/select", description = "**Selects entities in Foundry**")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object selectPost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            ,
            @QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world") String clientId

            ,

            @Parameter(name = "body") @Valid Object body

    );

    /**
     * **Returns the currently selected entities**
     *
     * @param xApiKey  parameter value.
     * @param clientId Auth token to connect to specific Foundry world Response 200: OK
     */
    @GET
    @Path("/selected")
    @Produces({
            "application/json" })
    @Operation(operationId = "Utilities_selectedGet", summary = "/selected", description = "**Returns the currently selected entities**")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object selectedGet(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            ,
            @QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world") String clientId

    );

}
