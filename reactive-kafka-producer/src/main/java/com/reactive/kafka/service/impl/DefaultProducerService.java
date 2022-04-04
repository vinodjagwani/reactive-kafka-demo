package com.reactive.kafka.service.impl;

import com.reactive.kafka.dto.MessagePayload;
import com.reactive.kafka.producer.PublishMessage;
import com.reactive.kafka.service.ProducerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultProducerService implements ProducerService {


    PublishMessage publishMessage;

    @Override
    public void send(final String topic, final List<MessagePayload> value) {
        publishMessage.sendToKafka(topic, Flux.fromIterable(value));
    }
}
