package io.qkits.dubbo.consumer;


import io.qkits.demo.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class ApiConsumerApplication {

    public static void main(String[] args) {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(
                new ApplicationConfig("dubbo-demo-api-consumer")
        );

        reference.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        reference.setInterface(DemoService.class);
        DemoService service = reference.get();
        String message = service.sayHello("dubbo-api-consumer");
        System.out.println(String.format("the message is %s", message));
    }
}
