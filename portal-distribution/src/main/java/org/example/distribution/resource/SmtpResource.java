package org.example.distribution.resource;

import org.example.distribution.service.SmtpService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/smtp")
public class SmtpResource {

    @Inject
    SmtpService smtpService;

    @GET
    public void sendEmail(){
        smtpService.sendSimpleEmail();
    }
}
