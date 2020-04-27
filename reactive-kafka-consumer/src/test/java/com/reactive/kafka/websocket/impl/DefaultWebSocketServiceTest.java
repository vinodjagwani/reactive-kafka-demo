package com.reactive.kafka.websocket.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.kafka.websocket.dto.MetaData;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultWebSocketServiceTest  {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    DefaultWebSocketService webSocketService;

    @Test
    public void testSend() throws Exception{
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(MetaData.builder().build());
        doNothing().when(messagingTemplate).convertAndSend(any(String.class), any(MetaData.class));
        webSocketService.send("hello");
        verify(messagingTemplate, times(1)).convertAndSend(any(String.class), any(MetaData.class));
    }
}
