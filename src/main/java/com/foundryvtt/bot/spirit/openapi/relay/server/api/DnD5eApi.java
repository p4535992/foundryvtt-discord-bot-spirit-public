package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import java.util.Map;

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

@Tag(name = "DnD5e")
@Path("/dnd5e")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface DnD5eApi {

    /**
     * 
     *
     * @param xApiKey    parameter value.
     * @param actorUuid  parameter value.
     * @param details    parameter value.
     * @param clientId   parameter value.
     * @param actorUuid2 parameter value.
     * @param details2   parameter value. Response 200: Successful response
     */
    @GET
    @Path("/get-actor-details")
    @Produces({
            "application/json" })
    @Operation(operationId = "DnD5e_dnd5eGetActorDetailsGet", summary = "get-actor-details")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successful response") })

    void dnd5eGetActorDetailsGet(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            ,

            @HeaderParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid

            ,

            @HeaderParam("details") @Parameter(name = "details") String details

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid2

            , @QueryParam("details") @Parameter(name = "details") String details2

    );

    /**
     * 
     *
     * @param xApiKey     parameter value.
     * @param clientId    parameter value.
     * @param actorUuid   parameter value.
     * @param amount      parameter value.
     * @param requestBody parameter value. Response 200: Successful response
     */
    @POST
    @Path("/modify-experience")
    @Consumes({
            "application/json" })
    @Produces({
            "application/json" })
    @Operation(operationId = "DnD5e_dnd5eModifyExperiencePost", summary = "modify-experience")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successful response") })

    void dnd5eModifyExperiencePost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid

            , @QueryParam("amount") @Parameter(name = "amount") Integer amount

            ,

            @Parameter(name = "request_body") @Valid Map<String, Object> requestBody

    );

    /**
     * 
     *
     * @param xApiKey     parameter value.
     * @param clientId    parameter value.
     * @param actorUuid   parameter value.
     * @param itemName    parameter value.
     * @param amount      parameter value.
     * @param requestBody parameter value. Response 200: Successful response
     */
    @POST
    @Path("/modify-item-charges")
    @Consumes({
            "application/json" })
    @Produces({
            "application/json" })
    @Operation(operationId = "DnD5e_dnd5eModifyItemChargesPost", summary = "modify-item-charges")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successful response") })

    void dnd5eModifyItemChargesPost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid

            , @QueryParam("itemName") @Parameter(name = "itemName") String itemName

            , @QueryParam("amount") @Parameter(name = "amount") String amount

            ,

            @Parameter(name = "request_body") @Valid Map<String, Object> requestBody

    );

    /**
     * 
     *
     * @param xApiKey     parameter value.
     * @param actorUuid   parameter value.
     * @param details     parameter value.
     * @param clientId    parameter value.
     * @param actorUuid2  parameter value.
     * @param abilityName parameter value.
     * @param requestBody parameter value. Response 200: Successful response
     */
    @POST
    @Path("/use-ability")
    @Consumes({
            "application/json" })
    @Produces({
            "application/json" })
    @Operation(operationId = "DnD5e_dnd5eUseAbilityPost", summary = "use-item")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successful response") })

    void dnd5eUseAbilityPost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            ,

            @HeaderParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid

            ,

            @HeaderParam("details") @Parameter(name = "details") String details

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("actorUuid") @Parameter(name = "actorUuid") String actorUuid2

            , @QueryParam("abilityName") @Parameter(name = "abilityName") String abilityName

            ,

            @Parameter(name = "request_body") @Valid Map<String, Object> requestBody

    );

}
