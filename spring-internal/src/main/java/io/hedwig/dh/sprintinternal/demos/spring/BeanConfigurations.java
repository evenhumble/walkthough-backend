package io.hedwig.dh.sprintinternal.demos.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.hedwig.dh.sprintinternal.demos.spring.beans.Car;


@Configuration
public class BeanConfigurations {

    @Bean(name = "car")
    public Car buildCar(){
        Car car = new Car();
        car.setName("Car one");
        return car;
    }
}
