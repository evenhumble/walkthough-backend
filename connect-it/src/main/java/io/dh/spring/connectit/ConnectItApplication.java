package io.dh.spring.connectit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ConnectItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectItApplication.class,args);
    }
}
