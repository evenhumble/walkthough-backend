package io.qmeta.jpa.demos.multidatasource;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class UserDataSourceConf {
    @Primary
    @Bean(name = "userDB")
    @ConfigurationProperties("spring.datasource.userDB")
    public DataSource useDb(){
        return DataSourceBuilder.create().build();
    }

//    @Primary
//    @Bean(name = "propertyEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("userDB") DataSource dataSource) {
//        return builder.
//                withDataSource(dataSource)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "propertyTransactionManager")
//    public PlatformTransactionManager propertyTransactionManager(
//            @Qualifier("propertyEntityManagerFactory") EntityManagerFactory propertyEntityManagerFactory) {
//        return new JpaTransactionManager(propertyEntityManagerFactory);
//    }
}
