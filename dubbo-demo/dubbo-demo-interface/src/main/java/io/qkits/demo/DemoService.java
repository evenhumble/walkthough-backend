package io.qkits.demo;

import io.qkits.demo.entity.GreetMsg;

public interface DemoService {

    String sayHello(String name);
    GreetMsg greet(GreetMsg msg);
}
