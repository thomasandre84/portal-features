package org.example.distribution.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Sending EMails via SMTP
 */
@Slf4j
@ApplicationScoped
public class SmtpService {

    @Inject
    Mailer mailer;

    public void sendSimpleEmail(){
        mailer.send(Mail.withText("test2@test.org", "A simple test", "with a body"));
        log.info("Email sent");
    }
}
