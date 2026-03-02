package com.foundryvtt.bot.spirit.openapi.relay.server.api;

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

@Tag(name = "DnD5e")

@Path("/dnd5e")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface DnD5eApi {

    /**
     * 
     *
     * @param xApiKey 
     * @param actorUuid 
     * @param details 
     * @param clientId 
     * @param actorUuid2 
     * @param details2 
     * @return Successful response
     */
    @GET
    @Path("/get-actor-details")
    @Produces({ "application/json" })
    
    @Operation(operationId = "DnD5e_dnd5eGetActorDetailsGet", summary = "get-actor-details")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "Successful response") })
    
    void dnd5eGetActorDetailsGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,


@HeaderParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid


,


@HeaderParam("details") @Parameter(name = "details")  String details


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid2





,@QueryParam("details") @Parameter(name = "details")  String details2





);


    /**
     * 
     *
     * @param xApiKey 
     * @param clientId 
     * @param actorUuid 
     * @param amount 
     * @param requestBody 
     * @return Successful response
     */
    @POST
    @Path("/modify-experience")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "DnD5e_dnd5eModifyExperiencePost", summary = "modify-experience")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "Successful response") })
    
    void dnd5eModifyExperiencePost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid





,@QueryParam("amount") @Parameter(name = "amount")  Integer amount





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * 
     *
     * @param xApiKey 
     * @param clientId 
     * @param actorUuid 
     * @param itemName 
     * @param amount 
     * @param requestBody 
     * @return Successful response
     */
    @POST
    @Path("/modify-item-charges")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "DnD5e_dnd5eModifyItemChargesPost", summary = "modify-item-charges")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "Successful response") })
    
    void dnd5eModifyItemChargesPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid





,@QueryParam("itemName") @Parameter(name = "itemName")  String itemName





,@QueryParam("amount") @Parameter(name = "amount")  String amount





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);


    /**
     * 
     *
     * @param xApiKey 
     * @param actorUuid 
     * @param details 
     * @param clientId 
     * @param actorUuid2 
     * @param abilityName 
     * @param requestBody 
     * @return Successful response
     */
    @POST
    @Path("/use-ability")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "DnD5e_dnd5eUseAbilityPost", summary = "use-item")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "Successful response") })
    
    void dnd5eUseAbilityPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,


@HeaderParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid


,


@HeaderParam("details") @Parameter(name = "details")  String details


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("actorUuid") @Parameter(name = "actorUuid")  String actorUuid2





,@QueryParam("abilityName") @Parameter(name = "abilityName")  String abilityName





,



@Parameter(name = "request_body") @Valid  Map<String, Object> requestBody

);

}
