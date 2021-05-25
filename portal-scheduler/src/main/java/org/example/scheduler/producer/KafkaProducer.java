package org.example.scheduler.producer;

import io.jaegertracing.internal.JaegerSpanContext;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.opentracing.Tracer;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.example.util.KafkaHeaderUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class KafkaProducer {

    @Inject
    @Channel("sync-out")
    Emitter<String> syncEmitter;

    @Inject
    Tracer tracer;

    public String emitSyncRecord(String content) {
        String traceId = KafkaHeaderUtil.getUberTraceId((JaegerSpanContext) tracer.activeSpan().context());
        Message<String> message = KafkaHeaderUtil.getMessage(traceId, content);

        syncEmitter.send(message);
        return traceId;
    }
}
