package org.example.sync.resource;

import lombok.extern.slf4j.Slf4j;
import org.example.sync.service.email.EMailSyncService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Slf4j
@Path("/triggers")
public class TriggerResource {
    @Inject
    EMailSyncService eMailSyncService;

    @GET
    @Path("/emails")
    public void syncEmail(){
        log.info("Sync EMails");
        eMailSyncService.fetchEMails();
    }
}
