package io.hedwig.mocktry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MockAdminApp {

    public static void main(String[] args) {
        SpringApplication.run(MockAdminApp.class,args);
    }
}
