package com.reactive.kafka.service;


import com.reactive.kafka.dto.MessagePayload;

import java.util.List;

public interface ProducerService {

    void send(final String topic, final List<MessagePayload> value);
}
