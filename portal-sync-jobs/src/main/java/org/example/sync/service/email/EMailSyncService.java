package org.example.sync.service.email;

import jakarta.mail.*;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
@ApplicationScoped
public class EMailSyncService {

    public void fetchEMails() {

    }


    /**
     * Create an email object from the given message and account.
     *
     * @param account
     * @param message
     * @return
     */
    private Email createEmailFromMessage(AccountConfiguration account,
                                         final Message message) {
        try {
            final String[] header = message.getHeader("Message-ID");
            final String id = header[0];

            final String accountName = account.name();
            final String from = "" + message.getFrom()[0].toString();
            final String to = message.getAllRecipients()[0].toString();
            final String subject = message.getSubject();
            final String body = message.getContent().toString();
            final List<String> tags = Arrays.asList("inbox", accountName);

            final Email email = new Email(id, accountName, from, to, subject,
                    body, tags);

            return email;
        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }


}
