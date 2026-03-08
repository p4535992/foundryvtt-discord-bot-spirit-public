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

@Tag(name = "Sheet")

@Path("/sheet")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface SheetApi {

    /**
     * ## Returns raw HTML (or a string in a JSON response) for an entity  If returning HTML there are options for scale, tab to open (if available), and darkMode.  If returning JSON the HTML is untouched.
     *
     * @param xApiKey 
     * @param clientId Auth token to connect to specific Foundry world
     * @param selected (Optional) Tab to open if available
     * @param actor (Optional) Return dark mode HTML. Default &#x3D; false
     * @param scale 
     * @return OK
     */
    @GET
    @Produces({ "text/plain" })
    
    @Operation(operationId = "Sheet_sheetGet", summary = "/sheet", description = "## Returns raw HTML (or a string in a JSON response) for an entity  If returning HTML there are options for scale, tab to open (if available), and darkMode.  If returning JSON the HTML is untouched.")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    String sheetGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("selected") @Parameter(name = "selected", description = "(Optional) Tab to open if available")  Boolean selected





,@QueryParam("actor") @Parameter(name = "actor", description = "(Optional) Return dark mode HTML. Default = false")  Boolean actor





,@QueryParam("scale") @Parameter(name = "scale")  BigDecimal scale





);

}
