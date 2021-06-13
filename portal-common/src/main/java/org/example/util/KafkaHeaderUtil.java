package org.example.util;

import io.jaegertracing.internal.JaegerSpanContext;
import io.smallrye.reactive.messaging.kafka.IncomingKafkaRecordMetadata;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecordMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class KafkaHeaderUtil {
    static final String PARTITION = "partition";
    static final String ID = "uber-trace-id";

    private KafkaHeaderUtil() {
    }

    public static Optional<Integer> getTargetPartition(IncomingKafkaRecordMetadata metadata) {
        AtomicReference<Optional<Integer>> target = new AtomicReference<>(Optional.empty());
        Headers headers = metadata.getHeaders();
        headers.forEach(header -> {
            if (PARTITION.equals(header.key())) {
                BigInteger bi = new BigInteger(header.value());
                Integer targetPartition = bi.intValue();
                log.info("Target Partition: {}", targetPartition);
                target.set(Optional.of(targetPartition));
            }
        });

        return target.get();
    }

    public static Optional<String> getHeaderId(IncomingKafkaRecordMetadata metadata) {
        AtomicReference<Optional<String>> iD = new AtomicReference<>(Optional.empty());
        Headers headers = metadata.getHeaders();
        headers.forEach(header -> {
            if (ID.equals(header.key())) {
                String id = new String(header.value());
                log.info("Got UUID of: {}", id);
                iD.set(Optional.of(id));
            }
        });
        return iD.get();
    }

    public static Boolean equalId(String initId, Optional<String> receivedId) {
        if (receivedId.isPresent()) {
            String recId = receivedId.get();
            log.info("Init Id {}  with received Id: {}", initId, recId);
            return initId.equals(recId);
        }
        return false;
    }

    public static BigInteger getReplyTargetPartition(List<TopicPartition> partitionList) {
        int partitionSize = partitionList.size();
        int partitionIndex = new Random().nextInt(partitionSize);
        TopicPartition target = partitionList.get(partitionIndex); // for tests choose a fix partition
        BigInteger targetPartition = BigInteger.valueOf(target.partition());
        log.info("Identified TargetPartition: {}", targetPartition);
        return targetPartition;
    }

    public static OutgoingKafkaRecordMetadata<String> genRequestOutgoingKafkaRecordMetadata(BigInteger targetPartition, String id){
        RecordHeader headerId = new RecordHeader(ID, id.getBytes());
        RecordHeader headerPartition = new RecordHeader(PARTITION, targetPartition.toByteArray());

        return OutgoingKafkaRecordMetadata.<String>builder()
                .withHeaders(Arrays.asList(headerId, headerPartition))
                .build();
    }

    public static OutgoingKafkaRecordMetadata<String> genRequestOutgoingKafkaRecordMetadata(String id){
        RecordHeader headerId = new RecordHeader(ID, id.getBytes());

        return OutgoingKafkaRecordMetadata.<String>builder()
                .withHeaders(Collections.singletonList(headerId))
                .build();
    }

    public static OutgoingKafkaRecordMetadata<String> genResponseOutgoingKafkaRecordMetadata(Integer targetPartition, String id){
        RecordHeader headerId = new RecordHeader(ID, id.getBytes());
        return OutgoingKafkaRecordMetadata.<String>builder()
                .withHeaders(Collections.singletonList(headerId))
                .withPartition(targetPartition)
                .build();
    }

    public static String getUberTraceId(JaegerSpanContext spanCtx) {
        return spanCtx.getTraceId() + ":" +
                Long.toHexString(spanCtx.getSpanId()) + ":" +
                Long.toHexString(spanCtx.getParentId()) + ":" +
                Integer.toHexString(spanCtx.getFlags());
    }

    /*public static String getUberTraceId(SpanContext spanCtx) {
        return spanCtx.getTraceId() + ":" +
                spanCtx.getSpanId() + ":" +
                spanCtx.getTraceState() + ":" +
                spanCtx.getTraceFlags();
    }*/

    public static Message<String> getMessage(String traceId, String content) {
        OutgoingKafkaRecordMetadata<String> customHeader = KafkaHeaderUtil.genRequestOutgoingKafkaRecordMetadata(traceId);
        Metadata metadata = Metadata.of(customHeader);
        return Message.of(content, metadata);
    }
}
