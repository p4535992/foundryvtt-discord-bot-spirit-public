package com.foundryvtt.bot.spirit.openapi.relay.system.api;

import java.math.BigDecimal;

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

@Tag(name = "Macros")

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface MacrosApi {

    /**
     * ## Executes a macro
     *
     * @param uuid UUID of the macro to execute
     * @param xApiKey 
     * @param clientId Auth token to connect to specific Foundry world
     * @param body 
     * @return OK
     */
    @POST
    @Path("/macro/{uuid}/execute")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "Macros_macroUuidExecutePost", summary = "/macro/:uuid/execute", description = "## Executes a macro")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object macroUuidExecutePost(
@PathParam("uuid") @Parameter(name = "uuid", required = true, description = "UUID of the macro to execute") String uuid




,


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,



@Parameter(name = "body") @Valid  Object body

);


    /**
     * ## Returns all available macros
     *
     * @param xApiKey 
     * @param clientId Auth token to connect to specific Foundry world
     * @return OK
     */
    @GET
    @Path("/macros")
    @Produces({ "application/json" })
    
    @Operation(operationId = "Macros_macrosGet", summary = "/macros", description = "## Returns all available macros")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object macrosGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);

}
