package io.hedwig.modules.kafka.producers.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.hedwig.modules.kafka.producers.properties.ProducerConfigs;

/**
 * 1. author: patrick
 */
public class SimpleClient {

    public static void main(String[] args) {
        Producer<String,String> producer = new KafkaProducer<String, String>(ProducerConfigs.localDefault());
        for (int i = 100; i < 10000; i++) {
            producer.send(
                new ProducerRecord<>("test", "this is test" + i));
        }
        producer.close();
    }
}
