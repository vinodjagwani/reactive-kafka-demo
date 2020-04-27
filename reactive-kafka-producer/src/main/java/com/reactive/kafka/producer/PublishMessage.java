package com.reactive.kafka.producer;


import com.reactive.kafka.dto.MessagePayload;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface PublishMessage {

    void sendToKafka(final String topic, final Flux<MessagePayload> messages);
}
