package org.example.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path(ClientRuntimeConfigResource.BASE_URL)
public class ClientRuntimeConfigResource {

    static final String BASE_URL = "/config";

    @GET
    public Response getConfig(){
        return Response.ok("Some Config").build();
    }
}
