package com.reactive.kafka.producer.impl;

import com.reactive.kafka.dto.MessagePayload;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultPublishMessageTest {

    @Mock
    KafkaSender<String, String> kafkaSender;

    @InjectMocks
    DefaultPublishMessage publishMessage;

    @Test
    public void testSendToKafka() {
        when(kafkaSender.send(any())).thenReturn(Flux.empty());
        publishMessage.sendToKafka("demo-topic", Flux.fromIterable(Collections.singletonList(MessagePayload.builder().build())));
        verify(kafkaSender, times(1)).send(any(Publisher.class));
    }
}
