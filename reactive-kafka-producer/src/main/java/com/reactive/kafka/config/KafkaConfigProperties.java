package com.reactive.kafka.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaConfigProperties {


    String acks = "all";

    Client client = new Client();

    List<String> topics = new ArrayList<>(Collections.singletonList("demo-topic"));

    Bootstrap bootstrap = new Bootstrap();

    @Data
    static class Bootstrap {
        List<String> servers = new ArrayList<>(Collections.singletonList("localhost:9092"));
    }

    @Data
    static class Client {
        String id = "reactive-kafka-producer";
    }

}
