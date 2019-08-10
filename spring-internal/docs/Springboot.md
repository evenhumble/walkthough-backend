# SpringBoot

## Start

```java
SpringAppication.run(X.class,args);
```

## SpringApplicationBuilder

```java
new SpringApplicationBuilder()
    .showBanner(false)
    .sources(Parent.class)
    .child(Application.class)
    .run(args);
```

## ApplicationEvent,and Listener

- ContextRefreshedEvent
- ApplicationStartEvent
- ApplicationEnvironmentPreparedEvent
- CommandLineRunner

## Configuration

- application.yml/properties
- profiles
- all properties in Spring Environment
- Config files: 
    * config folder
    * classpath: config
    * classpath

```shell
$ java -jar myproject.jar --spring.config.name=myproject
//or
$ java -jar myproject.jar --spring.config.location=classpath:/default.properties
        ,classpath:/override.properties
```

## Profiles/@Profile

load different configuration in different environment

## Logging


## MVC

Spring Boot为Spring MVC提供适用于多数应用的自动配置功能。在Spring默认基础上，自动配置添加了以下特性：

1. 引入ContentNegotiatingViewResolver和BeanNameViewResolver beans。
2. 对静态资源的支持，包括对WebJars的支持。
3. 自动注册Converter，GenericConverter，Formatter beans。
4. 对HttpMessageConverters的支持。
5. 自动注册MessageCodeResolver。
6. 对静态index.html的支持。
7. 对自定义Favicon的支持。

webjars
static/template/


* 内嵌servlet容器支持

Spring Boot支持内嵌的Tomcat, Jetty和Undertow服务器。多数开发者只需要使用合适的'Starter POM'来获取一个完全配置好的实例即可。默认情况下，内嵌的服务器会在8080端口监听HTTP请求。

**1.Servlets和Filters**

当使用内嵌的servlet容器时，你可以直接将servlet和filter注册为Spring的beans。在配置期间，如果你想引用来自application.properties的值，这是非常方便的。默认情况下，如果上下文只包含单一的Servlet，那它将被映射到根路径（/）。在多Servlet beans的情况下，bean的名称将被用作路径的前缀。过滤器会被映射到/*。
    
如果基于约定（convention-based）的映射不够灵活，你可以使用ServletRegistrationBean和FilterRegistrationBean类实现完全的控制。如果你的bean实现了ServletContextInitializer接口，也可以直接注册它们。

**2.EmbeddedWebApplicationContext**

Spring Boot底层使用了一个新的ApplicationContext类型，用于对内嵌servlet容器的支持。EmbeddedWebApplicationContext是一个特殊类型的WebApplicationContext，它通过搜索一个单一的EmbeddedServletContainerFactory bean来启动自己。通常，TomcatEmbeddedServletContainerFactory，JettyEmbeddedServletContainerFactory或UndertowEmbeddedServletContainerFactory将被自动配置。

**注**：你通常不需要知道这些实现类。大多数应用将被自动配置，并根据你的行为创建合适的ApplicationContext和EmbeddedServletContainerFactory。

**3.自定义内嵌servlet容器**

常见的Servlet容器设置可以通过Spring Environment属性进行配置。通常，你会把这些属性定义到application.properties文件中。
常见的服务器设置包括：

1. server.port - 进来的HTTP请求的监听端口号
2. server.address - 绑定的接口地址
3. server.sessionTimeout - session超时时间

具体参考[ServerProperties](http://github.com/spring-projects/spring-boot/tree/master/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/web/ServerProperties.java)。

**编程方式的自定义**

如果需要以编程的方式配置内嵌的servlet容器，你可以注册一个实现EmbeddedServletContainerCustomizer接口的Spring bean。EmbeddedServletContainerCustomizer提供对ConfigurableEmbeddedServletContainer的访问，ConfigurableEmbeddedServletContainer包含很多自定义的setter方法。
```java
import org.springframework.boot.context.embedded.*;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(9000);
    }
}
```
**直接自定义ConfigurableEmbeddedServletContainer**

如果上面的自定义手法过于受限，你可以自己注册TomcatEmbeddedServletContainerFactory，JettyEmbeddedServletContainerFactory或UndertowEmbeddedServletContainerFactory。
```java
@Bean
public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
    factory.setPort(9000);
    factory.setSessionTimeout(10, TimeUnit.MINUTES);
    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html");
    return factory;
}
```
很多可选的配置都提供了setter方法，也提供了一些受保护的钩子方法以满足你的某些特殊需求。具体参考相关文档。

**4.JSP的限制**

在内嵌的servlet容器中运行一个Spring Boot应用时（并打包成一个可执行的存档archive），容器对JSP的支持有一些限制。

1. tomcat只支持war的打包方式，不支持可执行的jar。
2. 内嵌的Jetty目前不支持JSPs。
3. Undertow不支持JSPs。

这里有个[JSP示例](http://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-web-jsp)，你可以查看如何设置相关事项。

### 安全
如果Spring Security在classpath下，那么web应用默认对所有的HTTP路径（也称为终点，端点，表示API的具体网址）使用'basic'认证。为了给web应用添加方法级别的保护，你可以添加@EnableGlobalMethodSecurity并使用想要的设置。其他信息参考[Spring Security Reference](http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle#jc-method)。

默认的AuthenticationManager有一个单一的user（'user'的用户名和随机密码会在应用启动时以INFO日志级别打印出来）。如下：
```java
Using default security password: 78fa095d-3f4c-48b1-ad50-e24c31d5cf35
```
**注**：如果你对日志配置进行微调，确保`org.springframework.boot.autoconfigure.security`类别能记录INFO信息，否则默认的密码不会被打印。

你可以通过提供`security.user.password`改变默认的密码。这些和其他有用的属性通过[SecurityProperties](http://github.com/spring-projects/spring-boot/tree/master/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/security/SecurityProperties.java)（以security为前缀的属性）被外部化了。

默认的安全配置（security configuration）是在SecurityAutoConfiguration和导入的类中实现的（SpringBootWebSecurityConfiguration用于web安全，AuthenticationManagerConfiguration用于与非web应用也相关的认证配置）。你可以添加一个@EnableWebSecurity bean来彻底关掉Spring Boot的默认配置。为了对它进行自定义，你需要使用外部的属性配置和WebSecurityConfigurerAdapter类型的beans（比如，添加基于表单的登陆）。在[Spring Boot示例](http://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/)里有一些安全相关的应用可以带你体验常见的用例。

在一个web应用中你能得到的基本特性如下：

1. 一个使用内存存储的AuthenticationManager bean和唯一的user（查看SecurityProperties.User获取user的属性）。
2. 忽略（不保护）常见的静态资源路径（`/css/**, /js/**, /images/**`和 `**/favicon.ico`）。
3. 对其他的路径实施HTTP Basic安全保护。
4. 安全相关的事件会发布到Spring的ApplicationEventPublisher（成功和失败的认证，拒绝访问）。
5. Spring Security提供的常见底层特性（HSTS, XSS, CSRF, 缓存）默认都被开启。

上述所有特性都能打开和关闭，或使用外部的配置进行修改（security.*）。为了覆盖访问规则（access rules）而不改变其他自动配置的特性，你可以添加一个使用@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)注解的WebSecurityConfigurerAdapter类型的@Bean。

如果Actuator也在使用，你会发现：

1. 即使应用路径不受保护，被管理的路径也会受到保护。
2. 安全相关的事件被转换为AuditEvents（审计事件），并发布给AuditService。
3. 默认的用户有ADMIN和USER的角色。

使用外部属性能够修改Actuator（执行器）的安全特性（management.security.*）。为了覆盖应用程序的访问规则，你可以添加一个WebSecurityConfigurerAdapter类型的@Bean。同时，如果不想覆盖执行器的访问规则，你可以使用@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)注解该bean，否则使用@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)注解该bean。

### 使用SQL数据库
Spring框架为使用SQL数据库提供了广泛的支持。从使用JdbcTemplate直接访问JDBC到完全的对象关系映射技术，比如Hibernate。Spring Data提供一个额外的功能，直接从接口创建Repository实现，并使用约定从你的方法名生成查询。

* 配置DataSource

Java的javax.sql.DataSource接口提供了一个标准的使用数据库连接的方法。传统做法是，一个DataSource使用一个URL连同相应的证书去初始化一个数据库连接。

**1.对内嵌数据库的支持**

开发应用时使用内存数据库是很实用的。显而易见地，内存数据库不需要提供持久化存储。你不需要在应用启动时填充数据库，也不需要在应用结束时丢弃数据。

Spring Boot可以自动配置的内嵌数据库包括[H2](http://www.h2database.com/), [HSQL](http://hsqldb.org/)和[Derby](http://db.apache.org/derby/)。你不需要提供任何连接URLs，只需要简单的添加你想使用的内嵌数据库依赖。

示例：典型的POM依赖如下：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.hsqldb</groupId>
    <artifactId>hsqldb</artifactId>
    <scope>runtime</scope>
</dependency>
```
**注**：对于自动配置的内嵌数据库，你需要依赖spring-jdbc。在示例中，它通过`spring-boot-starter-data-jpa`被传递地拉过来了。

**2.连接到一个生产环境数据库**

在生产环境中，数据库连接可以使用DataSource池进行自动配置。下面是选取一个特定实现的算法：

- 由于Tomcat数据源连接池的性能和并发，在tomcat可用时，我们总是优先使用它。
- 如果HikariCP可用，我们将使用它。
- 如果Commons DBCP可用，我们将使用它，但在生产环境不推荐使用它。
- 最后，如果Commons DBCP2可用，我们将使用它。

如果你使用spring-boot-starter-jdbc或spring-boot-starter-data-jpa 'starter POMs'，你将会自动获取对tomcat-jdbc的依赖。

**注**：其他的连接池可以手动配置。如果你定义自己的DataSource bean，自动配置不会发生。

DataSource配置通过外部配置文件的spring.datasource.*属性控制。示例中，你可能会在application.properties中声明下面的片段：
```java
spring.datasource.url=jdbc:mysql://localhost/test
spring.datasource.username=dbuser
spring.datasource.password=dbpass
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
其他可选的配置可以查看[DataSourceProperties](http://github.com/spring-projects/spring-boot/tree/master/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/jdbc/DataSourceProperties.java)。同时注意你可以通过spring.datasource.*配置任何DataSource实现相关的特定属性：具体参考你使用的连接池实现的文档。

**注**：既然Spring Boot能够从大多数数据库的url上推断出driver-class-name，那么你就不需要再指定它了。对于一个将要创建的DataSource连接池，我们需要能够验证Driver是否可用，所以我们会在做任何事情之前检查它。比如，如果你设置spring.datasource.driverClassName=com.mysql.jdbc.Driver，然后这个类就会被加载。

**3.连接到一个JNDI数据库**

如果正在将Spring Boot应用部署到一个应用服务器，你可能想要用应用服务器内建的特性来配置和管理你的DataSource，并使用JNDI访问它。

spring.datasource.jndi-name属性可以用来替代spring.datasource.url，spring.datasource.username和spring.datasource.password去从一个特定的JNDI路径访问DataSource。比如，下面application.properties中的片段展示了如何获取JBoss定义的DataSource：
```java
spring.datasource.jndi-name=java:jboss/datasources/customers
```
* 使用JdbcTemplate

Spring的JdbcTemplate和NamedParameterJdbcTemplate类是被自动配置的，你可以在自己的beans中通过@Autowire直接注入它们。
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // ...
}
```
* JPA和Spring Data

Java持久化API是一个允许你将对象映射为关系数据库的标准技术。spring-boot-starter-data-jpa POM提供了一种快速上手的方式。它提供下列关键的依赖：

- Hibernate - 一个非常流行的JPA实现。
- Spring Data JPA - 让实现基于JPA的repositories更容。
- Spring ORMs - Spring框架的核心ORM支持。

**注**：我们不想在这涉及太多关于JPA或Spring Data的细节。你可以参考来自[spring.io](http://spring.io/)的指南[使用JPA获取数据](http://spring.io/guides/gs/accessing-data-jpa/)，并阅读[Spring Data JPA](http://projects.spring.io/spring-data-jpa/)和[Hibernate](http://hibernate.org/orm/documentation/)的参考文档。

**1.实体类**

传统上，JPA实体类被定义到一个persistence.xml文件中。在Spring Boot中，这个文件不是必需的，并被'实体扫描'替代。默认情况下，在你主（main）配置类（被@EnableAutoConfiguration或@SpringBootApplication注解的类）下的所有包都将被查找。

任何被@Entity，@Embeddable或@MappedSuperclass注解的类都将被考虑。一个普通的实体类看起来像下面这样：
```java
package com.example.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    // ... additional members, often include @OneToMany mappings

    protected City() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public City(String name, String state) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }
    // ... etc
}
```
**注**：你可以使用@EntityScan注解自定义实体扫描路径。具体参考[Section 67.4, “Separate @Entity definitions from Spring configuration”](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#howto-separate-entity-definitions-from-spring-configuration)。

**2.Spring Data JPA仓库**

Spring Data JPA仓库（repositories）是用来定义访问数据的接口。根据你的方法名，JPA查询会被自动创建。比如，一个CityRepository接口可能声明一个findAllByState(String state)方法，用来查找给定状态的所有城市。

对于比较复杂的查询，你可以使用Spring Data的[Query](http://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/Query.html)来注解你的方法。

Spring Data仓库通常继承自[Repository](http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/Repository.html)或[CrudRepository](http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)接口。如果你使用自动配置，包括在你的主配置类（被@EnableAutoConfiguration或@SpringBootApplication注解的类）的包下的仓库将会被搜索。
 
下面是一个传统的Spring Data仓库：
```java
package com.example.myapp.domain;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;

public interface CityRepository extends Repository<City, Long> {

    Page<City> findAll(Pageable pageable);

    City findByNameAndCountryAllIgnoringCase(String name, String country);
}
```
**注**：我们仅仅触及了Spring Data JPA的表面。具体查看它的[参考指南](http://projects.spring.io/spring-data-jpa/)。

**3.创建和删除JPA数据库**

默认情况下，只有在你使用内嵌数据库（H2, HSQL或Derby）时，JPA数据库才会被自动创建。你可以使用spring.jpa.*属性显示的设置JPA。比如，为了创建和删除表你可以将下面的配置添加到application.properties中：
```java
spring.jpa.hibernate.ddl-auto=create-drop
```
**注**：Hibernate自己内部对创建，删除表支持（如果你恰好记得这回事更好）的属性是hibernate.hbm2ddl.auto。使用spring.jpa.properties.*（前缀在被添加到实体管理器之前会被剥离掉），你可以设置Hibernate本身的属性，比如hibernate.hbm2ddl.auto。示例：`spring.jpa.properties.hibernate.globally_quoted_identifiers=true`将传递hibernate.globally_quoted_identifiers到Hibernate实体管理器。

默认情况下，DDL执行（或验证）被延迟到ApplicationContext启动。这也有一个spring.jpa.generate-ddl标识，如果Hibernate自动配置被激活，那该标识就不会被使用，因为ddl-auto设置粒度更细。

### 使用NoSQL技术
* Redis
  1. 连接Redis
* MongoDB
  1. 连接MongoDB数据库
  2. MongoDBTemplate
  3. Spring Data MongoDB仓库
* Gemfire
* Solr
  1. 连接Solr
  2. Spring Data Solr仓库 
* Elasticsearch
  1. 连接Elasticsearch
  2. Spring Data Elasticseach仓库
  
### 消息
* JMS
  1. HornetQ支持
  2. ActiveQ支持
  3. 使用JNDI ConnectionFactory
  4. 发送消息
  5. 接收消息

### 发送邮件

### 使用JTA处理分布式事务
* 使用一个Atomikos事务管理器
* 使用一个Bitronix事务管理器
* 使用一个J2EE管理的事务管理器
* 混合XA和non-XA的JMS连接
* 支持可替代的内嵌事务管理器

### Spring集成

### 基于JMX的监控和管理

### 测试
* 测试作用域依赖
* 测试Spring应用
* 测试Spring Boot应用
  1. 使用Spock测试Spring Boot应用
* 测试工具
  1. ConfigFileApplicationContextInitializer
  2. EnvironmentTestUtils
  3. OutputCapture
  4. TestRestTemplate

### 开发自动配置和使用条件
* 理解auto-configured beans
* 定位auto-configuration候选者
* Condition注解
  1. Class条件
  2. Bean条件
  3. Property条件
  4. Resource条件
  5. Web Application条件
  6. SpEL表达式条件

### WebSockets