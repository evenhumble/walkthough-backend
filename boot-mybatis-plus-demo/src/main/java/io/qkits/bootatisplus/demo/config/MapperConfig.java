package io.qkits.bootatisplus.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.qkits.*.*.mapper")
public class MapperConfig {
}
