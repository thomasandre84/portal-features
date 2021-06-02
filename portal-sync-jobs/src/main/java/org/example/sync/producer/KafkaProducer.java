package org.example.sync.producer;

import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public final class KafkaProducer {

    @Channel("command-out")
    Emitter<JsonObject> commandEmitter;

    public void sendCommand() {

    }
}
