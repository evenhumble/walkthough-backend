package io.qkits.kafkaservice.producers.properties;

import java.util.Properties;

/**
 * 1. author: patrick
 */
public class ProducerConfigs {

    private static Properties localProperties;

    public synchronized static Properties localDefault() {
        if (localProperties == null) {
            localProperties = new Properties();
            localProperties.put("bootstrap.servers", "localhost:9092");
            localProperties.put("acks", "all");
            localProperties.put("retries", 0);
            localProperties.put("batch.size", 16384);
            localProperties.put("linger.ms", 1);
            localProperties.put("buffer.memory", 33554432);
            localProperties
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            localProperties
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }

        return localProperties;
    }
}
