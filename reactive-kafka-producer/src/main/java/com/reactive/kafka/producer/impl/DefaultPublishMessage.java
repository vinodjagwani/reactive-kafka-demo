package com.reactive.kafka.producer.impl;

import com.reactive.kafka.producer.PublishMessage;
import com.reactive.kafka.dto.MessagePayload;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultPublishMessage implements PublishMessage {


    KafkaSender<String, String> kafkaSender;

    @Override
    public void sendToKafka(final String topic, final Flux<MessagePayload> messages) {
        kafkaSender.send(messages.map(m -> SenderRecord.create(new ProducerRecord<>(topic, m.getMetaData(), m.getMetaData()), m.getMetaData())))
                .doOnError(e -> log.error("Send failed", e)).subscribe(r -> log.info("{}", r.recordMetadata()));
    }
}
