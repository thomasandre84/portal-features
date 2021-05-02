package org.example.sync.resource;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Slf4j
@Path("/trigger")
public class TriggerResource {

    @GET
    @Path("/emails")
    public void syncEmail(){
        log.info("Sync EMails");

    }
}
