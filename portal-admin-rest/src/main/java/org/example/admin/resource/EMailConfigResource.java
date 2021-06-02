package org.example.admin.resource;

import org.example.admin.service.EMailConfigService;
import org.example.model.EMailConfiguration;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.example.admin.resource.EMailConfigResource.BASE_URL;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(BASE_URL)
public final class EMailConfigResource {
    static final String BASE_URL = "/emails";

    @Inject
    EMailConfigService eMailConfigService;

    @GET
    public Response getConfigs() {
        return Response.ok(eMailConfigService.getConfigs()).build();
    }

    @POST
    public Response addConfig(EMailConfiguration configuration) {
        try {
            return Response.created(eMailConfigService.addConfig(configuration)).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

}
