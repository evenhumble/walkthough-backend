package io.hedwig.kafkatry.controller;

import io.hedwig.kafkatry.dto.ProduceDTO;
import io.hedwig.kafkatry.producer.KProducer;
import io.hedwig.kafkatry.producer.KProducerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class KafkaWebController {

    @Autowired
    KProducerFactory kafkaProducerFactory;

//    @PostMapping("/kafka/default/{topicName}")
//    public String sendToTopic(@PathVariable String topicName, @RequestBody String message) {
//        kafkaSender.send(topicName, message);
//        return "Message sent";
//    }

    @PostMapping("/kafka/producer")
    public ResponseEntity<String> produceKafkaMsg(@RequestBody ProduceDTO dto) {
        try {
            KProducer producer = kafkaProducerFactory.makeProducer(dto);
            producer.send(dto.getTopic(), dto.getData().toString());
            return ResponseEntity.ok("send successful!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
