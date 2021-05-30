package org.example.sync.service.email;

import io.smallrye.mutiny.Multi;
import jakarta.mail.*;
import lombok.extern.slf4j.Slf4j;
import org.example.model.EMail;
import org.example.model.EMailConfiguration;
import org.example.sync.repository.EMailConfigRepository;
import org.example.sync.repository.EMailRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ApplicationScoped
public class EMailSyncService {

    @Inject
    EMailConfigRepository eMailConfigRepository;

    @Inject
    EMailRepository eMailRepository;

    @Inject
    EMailGateway eMailGateway;

    public void fetchEMails() {
        List<EMailConfiguration> configurations = eMailConfigRepository.listAll();

        Multi.createFrom().items(configurations.stream())
                .onItem().transform(c -> eMailGateway.receive(c))
                .onItem().transform(msgs -> Arrays.stream(msgs))
                .onItem().transform(msgs -> msgs.map(this::createEmailFromMessage))
                .subscribe().with(
                        item -> eMailRepository.persist(item),
                        throwable -> log.warn("Failed to save Email {}", throwable),
                        () ->log.info("Fetching Emails Completed")
                );
    }


    /**
     * Create an email object from the given message and account.
     *
     * @param message
     * @return
     */
    private EMail createEmailFromMessage(final Message message) {
        try {
            final String[] header = message.getHeader("Message-ID");
            final String id = header[0];

            final String from = "" + message.getFrom()[0].toString();
            final String to = message.getAllRecipients()[0].toString();
            final String subject = message.getSubject();
            final String body = message.getContent().toString();

            final EMail email = EMail.builder()
                    .messageId(id).from(from).to(to).subject(subject).body(body).build();

            return email;
        } catch (final MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
