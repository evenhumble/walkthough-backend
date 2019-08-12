package io.hedwig.dh.spring.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class DHSpringbootJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DHSpringbootJobApplication.class,args);
    }

    @Bean
    public ThreadPoolTaskScheduler taskExecutor(){
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(10);
        System.out.println("set asyncTaskMaxPoolSize to "+10);
        return executor;
    }

}
