package io.hedwig.modules.kafka.consumers.properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 1. author: patrick
 */
public class ConsumerConfigs {
    private static Properties localProperties;

    public synchronized static Properties localDefault() {
        if (localProperties == null) {
            localProperties = new Properties();
            localProperties.put("bootstrap.servers", "localhost:9092");
            localProperties.put("group.id", "test");
            localProperties.put("enable.auto.commit", "true");
            localProperties.put("auto.commit.interval.ms", "1000");
            localProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            localProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        }

        return localProperties;
    }
}
