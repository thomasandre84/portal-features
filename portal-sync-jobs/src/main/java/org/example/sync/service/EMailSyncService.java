package org.example.sync.service;

import jakarta.mail.*;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@ApplicationScoped
public class EMailSyncService {

    public void fetchEMails() {
        Properties props = null;
        Authenticator authenticator = null;
        Session session = Session.getInstance(props, authenticator);

        try(Store store = session.getStore()) {
            store.connect();
            Folder inbox = store.getFolder("INBOX");

            inbox.open(Folder.READ_WRITE);

            Message m = inbox.getMessage(1); // get Message # 1
            String subject = m.getSubject(); // get Subject
            Object content = m.getContent();
            inbox.close();
        } catch (MessagingException | IOException e) {

        }

    }
}
