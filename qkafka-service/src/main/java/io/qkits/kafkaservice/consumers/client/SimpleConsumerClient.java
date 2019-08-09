package io.qkits.kafkaservice.consumers.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

import io.qkits.kafkaservice.consumers.properties.ConsumerConfigs;


/**
 * 1. author: patrick
 */
public class SimpleConsumerClient {

  public static void main(String[] args) {
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(ConsumerConfigs.localDefault());
    consumer.subscribe(Arrays.asList("test"));
    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(100);
      for (ConsumerRecord<String, String> record : records) {
        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
                          record.value());
      }
    }
  }
}
