package io.qkits.demo.api;

import io.qkits.demo.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class DemoApiProviderApplication {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        service.setInterface(DemoService.class);
        service.setRef(new DemoApiProvider());
        service.export();
        System.in.read();
    }
}