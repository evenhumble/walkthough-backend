package io.qdriven.springinshort.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * QMETA
 * created at: 2020/4/25
 * created by: patrick
 **/
public class IoCRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(IocConfig.class);
        DomainService service = context.getBean(DomainService.class);
        System.out.println(service);
        System.out.println(service.process1());
    }
}
