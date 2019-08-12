package io.hedwig.dh.sprintinternal.demos;

import java.beans.Beans;


public class AnnotationApplicationContext {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = (Car) ctx.getBean("car");
        System.out.println(car.getName());
    }
}