package com.reactive.kafka.websocket.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.kafka.websocket.WebSocketService;
import com.reactive.kafka.websocket.dto.MetaData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultWebSocketService implements WebSocketService {

    ObjectMapper objectMapper;

    SimpMessagingTemplate messagingTemplate;

    @Override
    public void send(final String message) {
        final MetaData data;
        try {
            data = objectMapper.readValue(message, MetaData.class);
            messagingTemplate.convertAndSend("/topic/websocket", data);
        } catch (final JsonProcessingException e) {
            log.error("Can't parse message ", e);
        }
    }
}
