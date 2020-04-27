package com.reactive.kafka.consumer;

import com.reactive.kafka.websocket.WebSocketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SubscribeMessage {


    WebSocketService webSocketService;

    KafkaReceiver<Object, Object> kafkaReceiver;


    public void receive() {
        kafkaReceiver.receive().subscribe(this::sendToSocket);
    }

    private void sendToSocket(final ReceiverRecord<Object, Object> record) {
        webSocketService.send(record.value().toString());
    }

}
