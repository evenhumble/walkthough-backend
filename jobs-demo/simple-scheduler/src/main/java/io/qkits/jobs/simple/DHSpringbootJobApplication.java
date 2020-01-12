package io.qkits.jobs.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DHSpringbootJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DHSpringbootJobApplication.class,args);
    }
}
