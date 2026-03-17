package com.foundryvtt.bot.spirit.openapi.relay.server.api;

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

@Tag(name = "Structure")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface StructureApi {

    /**
     * ## Returns the contents of a folder or compendium
     *
     * @param path Folder or compendium to return
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * Response 200: OK
     */
    @GET
    @Path("/contents/{path}")
    @Produces({ "application/json" })
    @Operation(operationId = "Structure_contentsPathGet", summary = "/contents/:path", description = "## Returns the contents of a folder or compendium")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object contentsPathGet(
@PathParam("path") @Parameter(name = "path", required = true, description = "Folder or compendium to return") String path




,


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);


    /**
     * ## Returns the folders and compendiums in the world
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * Response 200: OK
     */
    @GET
    @Path("/structure")
    @Produces({ "application/json" })
    @Operation(operationId = "Structure_structureGet", summary = "/structure", description = "## Returns the folders and compendiums in the world")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object structureGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





);

}
