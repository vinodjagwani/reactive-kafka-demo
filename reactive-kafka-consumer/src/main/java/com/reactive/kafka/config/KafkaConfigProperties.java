package com.reactive.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfigProperties {

    Client client = new Client();

    Group group = new Group();

    Bootstrap bootstrap = new Bootstrap();

    List<String> topics = new ArrayList<>(Collections.singletonList("demo-topic"));

    @Data
    static class Bootstrap {
        List<String> servers = new ArrayList<>(Collections.singletonList("localhost:9092"));
    }

    @Data
    static class Client {
        String id = "reactive-kafka-consumer";
    }

    @Data
    static class Group {
        String id = "reactive-kafka-group";
    }
}
