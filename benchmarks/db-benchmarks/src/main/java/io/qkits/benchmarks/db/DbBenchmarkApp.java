package io.qkits.benchmarks.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@SpringBootApplication
@EnableAsync
public class DbBenchmarkApp {

    public static void main(String[] args) {
        SpringApplication.run(DbBenchmarkApp.class,args);
    }
}
