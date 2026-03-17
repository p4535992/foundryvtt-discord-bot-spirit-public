package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import java.io.File;

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

@Tag(name = "FileSystem")
@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")

public interface FileSystemApi {

    /**
     * 
     *
     * @param xApiKey  parameter value.
     * @param clientId parameter value.
     * @param path     parameter value.
     * @param format   parameter value. Response 200: OK
     */
    @GET
    @Path("/download")
    @Produces({
            "application/json" })
    @Operation(operationId = "FileSystem_downloadGet", summary = "/download")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object downloadGet(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("path") @Parameter(name = "path") String path

            , @QueryParam("format") @Parameter(name = "format") String format

    );

    /**
     * 
     *
     * @param xApiKey  parameter value.
     * @param clientId parameter value. Response 200: OK
     */
    @GET
    @Path("/file-system")
    @Produces({
            "application/json" })
    @Operation(operationId = "FileSystem_fileSystemGet", summary = "/file-system")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK") })

    Object fileSystemGet(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

    );

    /**
     * 
     *
     * @param xApiKey   parameter value.
     * @param clientId  parameter value.
     * @param path      parameter value.
     * @param filename  parameter value.
     * @param overwrite parameter value.
     * @param body      parameter value. Response 201: Created
     */
    @POST
    @Path("/upload")
    @Consumes({
            "text/plain" })
    @Produces({
            "application/json" })
    @Operation(operationId = "FileSystem_uploadPost", summary = "/upload")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Created") })

    Object uploadPost(

            @HeaderParam("x-api-key") @Parameter(name = "x-api-key") String xApiKey

            , @QueryParam("clientId") @Parameter(name = "clientId") String clientId

            , @QueryParam("path") @Parameter(name = "path") String path

            , @QueryParam("filename") @Parameter(name = "filename") String filename

            , @QueryParam("overwrite") @Parameter(name = "overwrite") Boolean overwrite

            ,

            @Parameter(name = "body") @Valid File body

    );

}
