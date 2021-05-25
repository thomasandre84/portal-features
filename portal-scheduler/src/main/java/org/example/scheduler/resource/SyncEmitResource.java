package org.example.scheduler.resource;

import org.example.scheduler.producer.KafkaProducer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/sync")
public class SyncEmitResource {

    @Inject
    KafkaProducer kafkaProducer;

    @GET
    public String sync() {
        return kafkaProducer.emitSyncRecord("test");
    }
}
