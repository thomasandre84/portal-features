package org.example.admin.resource;

import org.example.admin.service.EMailConfigService;
import org.example.model.EMailConfiguration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/emails")
public class EMailConfigResource {

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
