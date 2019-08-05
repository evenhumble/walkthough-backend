package io.hedwig.kafkatry.producer;

//import org.springframework.beans.factory.annotation.Autowired;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;

import java.util.concurrent.Future;
//import org.springframework.stereotype.Component;

//@Component
public class KProducer {
    private final static Logger logger = LoggerFactory.getLogger(KProducer.class);
    private KafkaProducer<String, String> producer;

    public Future<RecordMetadata> send(String topic, String payload) {
        logger.info("start to send message");
        ProducerRecord<String,String> record = new ProducerRecord<>(topic, payload);
        Future<RecordMetadata> meta= producer.send(record);
        producer.close();
        return meta;

    }

    public KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }
}