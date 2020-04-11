package io.qkits.jobs.simple.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskConfig {

    @Value("${demo.task.poolNum:10}")
    private int poolNum;

    @Bean(value = "taskExecutor")
    public ThreadPoolTaskScheduler taskExecutor(){
        System.out.println("start init thread pool executor");
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(poolNum);
        System.out.println("set asyncTaskMaxPoolSize to "+10);
        return executor;
    }
}
