package com.reactive.kafka.config;


import com.reactive.kafka.consumer.SubscribeMessage;
import com.reactive.kafka.websocket.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Map;


@Slf4j
@Configuration
@EnableConfigurationProperties(value = {KafkaConfigProperties.class})
public class KafkaConfig {

    @Autowired
    private KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public SubscribeMessage subscribeMessage(final KafkaReceiver<Object, Object> kafkaReceiver, final WebSocketService webSocketService) {
        SubscribeMessage subscribeMessage = new SubscribeMessage(webSocketService, kafkaReceiver);
        subscribeMessage.receive();
        return subscribeMessage;
    }

    @Bean
    public KafkaReceiver<Object, Object> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }



    private ReceiverOptions<Object, Object> receiverOptions() {
        return ReceiverOptions.create(Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                String.join(",", kafkaConfigProperties.bootstrap.getServers()),
                ConsumerConfig.CLIENT_ID_CONFIG, kafkaConfigProperties.client.getId(),
                ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProperties.group.getId(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
        )).subscription(kafkaConfigProperties.getTopics())
                .addAssignListener(partitions -> log.info("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> log.info("onPartitionsRevoked {}", partitions));
    }
}
