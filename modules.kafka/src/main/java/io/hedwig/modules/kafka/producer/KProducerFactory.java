package io.hedwig.kafkatry.producer;

import io.hedwig.kafkatry.dto.ProduceDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class KProducerFactory {
//    public static Map<String,KProducer> kafkaProducerMap = new ConcurrentHashMap<>();

    public KProducer makeProducer(ProduceDTO produceDTO){
        KProducer producer = new KProducer();
        KafkaProducer<String, String> kafkaClient =
                new KafkaProducer<>(makeProducerFactory(produceDTO));
        producer.setProducer(kafkaClient);
        return producer;
    }

    private Properties makeProducerFactory(ProduceDTO produceDTO){
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,produceDTO.getBootStrapServer());
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return kafkaProps;
    }

}
