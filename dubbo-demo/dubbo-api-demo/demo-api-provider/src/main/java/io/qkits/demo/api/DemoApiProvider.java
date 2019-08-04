package io.qkits.demo.api;

import io.qkits.demo.DemoService;
import io.qkits.demo.entity.GreetMsg;

public class DemoApiProvider implements DemoService {

    public String sayHello(String name) {
        System.out.println("this is from DemoService Api Provider");
        return "this is api provider "+name;
    }

    public GreetMsg greet(GreetMsg msg) {

        GreetMsg returnMsg = new GreetMsg();
        returnMsg.setFrom(msg.getFrom());
        returnMsg.setTo(msg.getTo());
        returnMsg.setMsg(msg.getMsg()+" replied ...");
        return returnMsg;
    }
}
