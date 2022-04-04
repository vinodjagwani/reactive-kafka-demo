package com.reactive.kafka.consumer;

import com.reactive.kafka.websocket.WebSocketService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscribeMessageTest {


    @Mock
    WebSocketService webSocketService;

    @Mock
    KafkaReceiver<Object, Object> kafkaReceiver;

    @InjectMocks
    SubscribeMessage subscribeMessage;


    @Test
    public void testReceive() {
        when(kafkaReceiver.receive()).thenReturn(Flux.just(
                new ReceiverRecord<>(new ConsumerRecord<>("demo-topic", 1, 1, Object.class, Object.class), null)));
        subscribeMessage.receive();
        verify(kafkaReceiver, times(1)).receive();
    }

}
