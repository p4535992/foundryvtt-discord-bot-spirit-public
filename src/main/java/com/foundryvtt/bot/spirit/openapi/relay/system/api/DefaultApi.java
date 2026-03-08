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



@Tag(name = "default")



@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface DefaultApi {

    /**
     * Returns the API status
     *
     * @return OK
     */
    @GET
    @Path("/api/status")
    @Produces({ "application/json" })
    
    @Operation(operationId = "default_apiStatusGet", summary = "/api/status", description = "Returns the API status")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object apiStatusGet();


    /**
     * Returns connected client Foundry Worlds
     *
     * @param xApiKey 
     * @return OK
     */
    @GET
    @Path("/clients")
    @Produces({ "application/json" })
    
    @Operation(operationId = "default_clientsGet", summary = "/clients", description = "Returns connected client Foundry Worlds")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object clientsGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


);

}
