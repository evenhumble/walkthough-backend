package io.qkits.benchmarks.db;

import io.qkits.benchmarks.db.core.BenchmarkRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
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
        SpringApplication.run(DbBenchmarkApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            if (args.length > 0) {
                BenchmarkRunner runner = ctx.getBean(BenchmarkRunner.class);
                for (String arg : args) {
                    runner.runBenchmarkByType(arg);
                }
            }
        };
    }
}
