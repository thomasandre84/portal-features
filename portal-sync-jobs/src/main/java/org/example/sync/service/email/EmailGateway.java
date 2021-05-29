package org.example.sync.service.email;
import java.util.Properties;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;


@Slf4j
@ApplicationScoped
public class EmailGateway {
    static final String INBOX = "INBOX";

    public Message[] receive(AccountConfiguration account) {
        final Properties props = setupPropertiesForAccount(account);
        final Session emailSession = Session.getInstance(props);
        final Store store = getStore(emailSession, account.protocol().name());

        connectToStore(account, store);

        final Folder emailFolder = getEmailFolderAccess(store);

        return getMessages(emailFolder);

    }

    private Store getStore(Session session, String storeType) {
        try {
            return session.getStore(storeType);
        } catch (final NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unchecked version of getMessages.
     *
     * @param emailFolder
     * @return
     */
    private Message[] getMessages(Folder emailFolder) {
        try {
            return emailFolder.getMessages();
        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read INBOX with read-write access.
     *
     * @param store
     * @return
     */
    private Folder getEmailFolderAccess(final Store store) {
        Folder emailFolder;
        try {
            emailFolder = store.getFolder(INBOX);
            emailFolder.open(Folder.READ_WRITE);
            return emailFolder;
        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Connect to the given store with the given account settings.
     *
     * @param account
     * @param store
     */
    private void connectToStore(AccountConfiguration account,
                                final Store store) {
        try {
            store.connect(account.host(),
                    account.username(),
                    account.password());
        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a properties map for POP3 access based on the account
     * configuration.
     *
     * @param account
     * @return
     */
    private Properties setupPropertiesForAccount(AccountConfiguration account) {
        final Properties props = new Properties();
        props.setProperty("mail.pop3.host",
                account.host());
        props.setProperty("mail.pop3.port",
                account.port().toString());
        props.setProperty("mail.user",
                account.username());
        props.setProperty("mail.password",
                account.password());
        return props;
    }






}
