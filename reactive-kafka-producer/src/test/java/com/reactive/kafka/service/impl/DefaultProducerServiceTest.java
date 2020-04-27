package com.reactive.kafka.service.impl;

import com.reactive.kafka.dto.MessagePayload;
import com.reactive.kafka.producer.PublishMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultProducerServiceTest {


    @Mock
    PublishMessage publishMessage;

    @InjectMocks
    DefaultProducerService producerService;

    @Test
    public void testSend() {
        doNothing().when(publishMessage).sendToKafka(any(String.class), any());
        producerService.send("demo-topic", Collections.singletonList(MessagePayload.builder().build()));
        verify(publishMessage, times(1)).sendToKafka(any(String.class), any());
    }
}
