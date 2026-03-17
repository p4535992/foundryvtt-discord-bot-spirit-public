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

@Tag(name = "Search")
@Path("/search")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface SearchApi {

    /**
     * ## Searches Foundry VTT entities using QuickInsert  Filters can be a single string for filtering by type (\&quot;actor\&quot;, \&quot;item\&quot;, ext.), or chained together (name:bob,documentType:actor)  Available filters:  - documentType: type of document (\&quot;Actor\&quot;, \&quot;Item\&quot;, ext) - folder: folder location of the entity (not always defined) - id: unique identifier of the entity - name: name of the entity - package: package identifier the entity belongs to (compendiums minus \&quot;Compendium.\&quot;) - packageName: human-readable package name (readable name of compendium) - subType: sub-type of the entity (\&quot;npc\&quot;, \&quot;equipment\&quot;, ext) - uuid: universal unique identifier - icon: icon HTML for the entity - journalLink: journal link to entity - tagline: same as packageName - formattedMatch: HTML with **applied to matching search parts** - **resultType: constructor name of the QuickInsert result type (\&quot;EntitySearchItem\&quot;. \&quot;CompendiumSearchItem\&quot;, \&quot;EmbeddedEntitySearchItem\&quot;, ext)**
     *
     * @param xApiKey parameter value.
     * @param clientId Auth token to connect to specific Foundry world
     * @param query Search string
     * @param filter parameter value.
     * Response 200: OK
     */
    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "Search_searchGet", summary = "/search", description = "## Searches Foundry VTT entities using QuickInsert  Filters can be a single string for filtering by type (\"actor\", \"item\", ext.), or chained together (name:bob,documentType:actor)  Available filters:  - documentType: type of document (\"Actor\", \"Item\", ext) - folder: folder location of the entity (not always defined) - id: unique identifier of the entity - name: name of the entity - package: package identifier the entity belongs to (compendiums minus \"Compendium.\") - packageName: human-readable package name (readable name of compendium) - subType: sub-type of the entity (\"npc\", \"equipment\", ext) - uuid: universal unique identifier - icon: icon HTML for the entity - journalLink: journal link to entity - tagline: same as packageName - formattedMatch: HTML with **applied to matching search parts** - **resultType: constructor name of the QuickInsert result type (\"EntitySearchItem\". \"CompendiumSearchItem\", \"EmbeddedEntitySearchItem\", ext)**")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object searchGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId", description = "Auth token to connect to specific Foundry world")  String clientId





,@QueryParam("query") @Parameter(name = "query", description = "Search string")  String query





,@QueryParam("filter") @Parameter(name = "filter")  String filter





);

}
