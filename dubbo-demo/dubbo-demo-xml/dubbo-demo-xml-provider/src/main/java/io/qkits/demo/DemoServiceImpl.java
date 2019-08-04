package io.qkits.demo;

import io.qkits.demo.entity.GreetMsg;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "name " + "xml provider";
    }

    public GreetMsg greet(GreetMsg msg) {
        GreetMsg returnMsg = new GreetMsg();
        returnMsg.setFrom(msg.getFrom());
        returnMsg.setTo(msg.getTo());
        returnMsg.setMsg(msg.getMsg() + " xml replied ...");
        return returnMsg;
    }
}
