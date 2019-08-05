package io.hedwig.dh.charts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
public class TryChartApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TryChartApplication.class,args);
    }

}
