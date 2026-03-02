package com.foundryvtt.bot.spirit.openapi.relay.server.api;


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

@Tag(name = "Utilities")

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface UtilitiesApi {

    /**
     * #### Executes Javascript in Foundry  Accepts the script as a file upload, or as a raw string in the body with the key \"script\".  If included as a raw string in the body excape quotes and backslashes, and remove comments.  Returns the result of the code execution.
     *
     * @param xApiKey 
     * @param clientId 
     * @param body 
     * @return OK
     */
    @POST
    @Path("/execute-js")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "Utilities_executeJsPost", summary = "/execute-js", description = "#### Executes Javascript in Foundry  Accepts the script as a file upload, or as a raw string in the body with the key \"script\".  If included as a raw string in the body excape quotes and backslashes, and remove comments.  Returns the result of the code execution.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object executeJsPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * **Selects entities in Foundry**
     *
     * @param xApiKey 
     * @param clientId Auth token to connect to specific Foundry world
     * @param body 
     * @return OK
     */
    @POST
    @Path("/select")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "Utilities_selectPost", summary = "/select", description = "**Selects entities in Foundry**")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object selectPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * **Returns the currently selected entities**
     *
     * @param xApiKey 
     * @param clientId Auth token to connect to specific Foundry world
     * @return OK
     */
    @GET
    @Path("/selected")
    @Produces({ "application/json" })
    
    @Operation(operationId = "Utilities_selectedGet", summary = "/selected", description = "**Returns the currently selected entities**")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object selectedGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);

}
