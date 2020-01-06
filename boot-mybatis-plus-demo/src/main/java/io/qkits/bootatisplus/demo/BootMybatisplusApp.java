package io.qkits.bootatisplus.demo;

import com.baomidou.mybatisplus.dts.EnableDtsRabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDtsRabbit
@SpringBootApplication
public class BootMybatisplusApp {
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
    public static void main(String[] args) {
        SpringApplication.run(BootMybatisplusApp.class,args);
    }
}
