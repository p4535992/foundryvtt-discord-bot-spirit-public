package com.foundryvtt.bot.spirit.openapi.relay.server.api;

import java.io.File;

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

@Tag(name = "FileSystem")

@Path("")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", comments = "Generator version: 7.19.0")
public interface FileSystemApi {

    /**
     * 
     *
     * @param xApiKey 
     * @param clientId 
     * @param path 
     * @param format 
     * @return OK
     */
    @GET
    @Path("/download")
    @Produces({ "application/json" })
    
    @Operation(operationId = "FileSystem_downloadGet", summary = "/download")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object downloadGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("path") @Parameter(name = "path")  String path





,@QueryParam("format") @Parameter(name = "format")  String format





);


    /**
     * 
     *
     * @param xApiKey 
     * @param clientId 
     * @return OK
     */
    @GET
    @Path("/file-system")
    @Produces({ "application/json" })
    
    @Operation(operationId = "FileSystem_fileSystemGet", summary = "/file-system")
    @APIResponses(value = { 
        @APIResponse(responseCode = "200", description = "OK") })
    
    Object fileSystemGet(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





);


    /**
     * 
     *
     * @param xApiKey 
     * @param clientId 
     * @param path 
     * @param filename 
     * @param overwrite 
     * @param body 
     * @return Created
     */
    @POST
    @Path("/upload")
    @Consumes({ "text/plain" })
    @Produces({ "application/json" })
    
    @Operation(operationId = "FileSystem_uploadPost", summary = "/upload")
    @APIResponses(value = { 
        @APIResponse(responseCode = "201", description = "Created") })
    
    Object uploadPost(


@HeaderParam("x-api-key") @Parameter(name = "x-api-key")  String xApiKey


,@QueryParam("clientId") @Parameter(name = "clientId")  String clientId





,@QueryParam("path") @Parameter(name = "path")  String path





,@QueryParam("filename") @Parameter(name = "filename")  String filename





,@QueryParam("overwrite") @Parameter(name = "overwrite")  Boolean overwrite





,



@Parameter(name = "body") @Valid  File body

);

}
