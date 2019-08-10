# Spring IoC Container

## @Bean/@Configuration

- @Bean/```<beans/>```
- @Configuration
  where beans comes from

```java
@Configuration
public class BeansConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

}
```

## AnnotationConfigApplicationContext initialize spring container

- configured beans which is defined in codes by annotation or xml config

```java
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    MyService myService = ctx.getBean(MyService.class);
    myService.doStuff();
}
```

also ApplicationContext include any other
```JSR-330``` defined classes,like ```@Component```,
```@Service```,```@Autowired``` ......

- register in runtime

```java
public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(AppConfig.class, OtherConfig.class);
    ctx.register(AdditionalConfig.class);
    ctx.refresh();
    MyService myService = ctx.getBean(MyService.class);
    myService.doStuff();
}
```

- scan(location...), declare where to find the configuration

```java
@Configuration
@ComponentScan(basePackages = "com.acme")
public class AppConfig  {
    ...
}
```

- AnnotationConfigWebApplicationContext for Web Application
    
    * ContextLoaderListener
    * DispatcherServlet
      * context-class:AnnotationConfigWebApplicationContext
      * contextConfigLocation: MVCConfig

- Mapping dispatcher to url-pattern*

## Injection

- JSR-250: PostConstruct/PreDestroy
- Beans Constructor Injection
- Bean LifeCycle
  * InitializingBean
  * DisposableBean
  * Lifecycle，
- Aware Interface
  * BeanFactoryAware
  * MessageResourceAware
  * BeanNameAware
  * ApplicationContextAware
- init-method/destroy-method

## Bean Scope

- @Scope
- singleton default scope, only one instance in spring IoC container
- prototype: create every time when invoked
- scoped-proxy

## Bean Name/Description

define bean name and description in annotation

## Bean Configuration

- Dependency
- Import

## Condition

## Import Resource