package io.hedwig.kafkatry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class KafkaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAdminApplication.class,args);
    }
}
