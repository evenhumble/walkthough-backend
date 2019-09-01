package io.hedwig.dh.sprintinternal.demos.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.hedwig.dh.sprintinternal.demos.spring.beans.Car;


public class AnnotationApplicationContext {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfigurations.class);
    Car car = (Car) ctx.getBean("car");
    System.out.println(car.getName());
    System.out.println(ctx.getBeanDefinitionCount());
    String[] names = ctx.getBeanDefinitionNames();
    for (String name : names) {
      System.out.println("name:"+name);
    }
  }
}