package org.example.distribution.consumer;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("email-in")
    @Traced
    public CompletionStage<Void> getEmailMessages(Message<JsonObject> emailMessage) {
        JsonObject object = emailMessage.getPayload();
        System.out.println(object);
        return emailMessage.ack();
    }
}
