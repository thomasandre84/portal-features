package org.example.admin.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path(RuntimeConfigResource.BASE_URL)
public class RuntimeConfigResource {

    static final String BASE_URL = "/config";
    @GET
    public Response getConfig(){
        return Response.ok("Some Config").build();
    }
}
