package io.qmeta.jpa.demos;

import io.qmeta.jpa.demos.generator.entity.msyql.Column;
import io.qmeta.jpa.demos.generator.entity.msyql.TableDO;
import io.qmeta.jpa.demos.generator.repository.ColumnRepo;
import io.qmeta.jpa.demos.generator.repository.TableRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JPADemoApp {
    public static void main(String[] args) {
        SpringApplication.run(JPADemoApp.class);
    }

    @Bean
    public CommandLineRunner mappingCheck(ColumnRepo columnRepo, TableRepo tableRepo){

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Column> columns = columnRepo.findByTableName("nodes");
                System.out.println(columns);
                List<TableDO> tables = tableRepo.findByTableName("nodes");
                System.out.println(tables);
            }
        };

    }
}
