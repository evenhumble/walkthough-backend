package io.hedwig.proxykata.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * @@author: patrick
 */
public class PersonService {
    public String sayHello(String name){
        return String.format("Hello "+name);
    }

    public Integer lengthOfName(String name){
        return name.length();
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello World");
        PersonService proxy = (PersonService) enhancer.create();
        String helloStr = proxy.sayHello("world!");
        System.out.println(helloStr);
    }
}
