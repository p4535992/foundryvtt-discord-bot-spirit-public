package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Represents a collection of functions to interact with the API endpoints.
 */

@Tag(name = "Docs")
@Path("/api/docs")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface DocsApi {

    /**
     * Returns the API documentation
     *
     * Response 200: OK
     */
    @GET
    @Produces({
            "application/json" })
    @Operation(operationId = "Docs_apiDocsGet", summary = "/api/docs", description = "Returns the API documentation")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object apiDocsGet();

}
