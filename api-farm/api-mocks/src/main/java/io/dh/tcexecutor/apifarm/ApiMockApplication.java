package io.dh.tcexecutor.apifarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApiMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMockApplication.class);
    }
}
