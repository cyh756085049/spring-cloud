---
title: Spring Cloud微服务
date: 2019-10-09 10:56:25
author: cyh
tags:
- Spring Cloud
- 微服务
categories: Java
summary: Spring Cloud微服务学习

---

##### Spring Cloud是一个快速构建分布式应用的工具集

> Spring Cloud微服务学习示例代码：https://github.com/cyh756085049/spring-cloud-study

### 一、构建分布式应用

#### 1、服务提供者和服务消费者的定义

| 名词       | 定义                                       |
| ---------- | ------------------------------------------ |
| 服务提供者 | 服务的被调用方（为其他服务提供服务的服务） |
| 服务消费者 | 服务的调用方（依赖其他服务的服务）         |

#### 2、搭建**`Spring Cloud`**框架

（1）先创建一个Maven项目

（2）在该Maven下新建一个Module，选择Spring Initializr，创建SpringBoot项目![image-20191009092353372](https://tva1.sinaimg.cn/large/00831rSTly1gcz2tdaigrj31cm0u0q7t.jpg)

（3）应用开发，遵循三步：加依赖；加注解；写配置

- 加依赖：pom.xml（一般依赖）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.8.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.provider</groupId>
    <artifactId>user</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>user</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR3</spring-cloud.version>
    </properties>

    <dependencies>
      	<!-- 提供了Spring Data JPA的支持 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
      	<!-- 提供了Spring MVC的支持 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
				<!-- h2 是一种内嵌的数据库，语法和MySQL类似 -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
      	<!-- 一款开发利器，可以帮助你简化掉N多冗余代码 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- 引入spring cloud的依赖，不能少，主要用来管理Spring Cloud生态各组件的版本 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

```

- 加注解（一般常用注解）：

  | 注解                                              | 注解含义解释                                                 |
  | ------------------------------------------------- | ------------------------------------------------------------ |
  | `@Table`                                          | 声明此对象映射到数据库的数据表                               |
  | `@Entity`                                         | 对实体注释                                                   |
  | `@Id`                                             | 声明此属性为主键                                             |
  | `@GeneratedValue(strategy = GenerationType.AUTO)` | 指定主键的生成策略：<br>TABLE：使用一个特定的数据库表格来保存主键。<br>SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。IDENTITY：主键由数据库自动生成（主要是自动增长型）。<br>AUTO：主键由程序控制。 |
  | `@Column`                                         | 声明该属性与数据库字段的映射关系                             |
  | `@Data`                                           | 用来指定实体对象的Getter和Setter方法                         |
  | `@AllArgsConstructor`                             | 用来指定全参数构造器                                         |
  | `@NoArgsConstructor`                              | 用来指定无参构造器                                           |
  | `@Repository`                                     | 用来表明该类是用来执行与数据库相关的操作（即dao对象）,作用于持久层 |
  | `@Service`                                        | 作用于业务逻辑层                                             |
  | `@Component`                                      | 是通用注解，当组件不好归类的时候，我们就可以用这个注解进行标注 |
  | `@Controller`                                     | 是spring-mvc的注解，具有将请求进行转发，重定向的功能。返回的是字符串，或者是字符串匹配的模板名称，即直接渲染视图，与html页面配合使用的，返回到指定页面。 |
  | `@RestController`                                 | 相当于`@ResponseBody ＋ @Controller`合在一起的作用。是将方法返回的对象直接在浏览器上展示成json格式 |
  | `@RequestMapping`                                 | 用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径；用于方法上，表示在类的父路径下追加方法上注解中的地址将会访问到该方法，例如：`@RequestMapping(value = "/selectByCondition",method = RequestMethod.*GET*)` |
  | `@RequestParam`                                   | 配合 @RequestMapping 一起使用，可以将请求的参数同处理方法的参数绑定在一起，例如：`@RequestParam(value = "nowPage", defaultValue = "1"，required = false)`required 这个参数定义了参数值是否是必须要传的 |
  | `@PathVariable`                                   | 用来获取请求路径（url ）中的动态参数。例如：`@RequestMapping(value = "user/login/{id}/{name}/{status}") `中的 `{id}/{name}/{status}`与 `@PathVariable int id、@PathVariable String name、@PathVariable boolean status`一一对应，按名匹配。 |
  | `@ResponseBody`                                   | 表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，返回值通常解析为跳转路径，加上 `@Responsebody` 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。会直接返回 json 数据。例如：`@RequestBody Thesis t` |
  | `@GetMapping`                                     | 是`@RequestMapping(method = RequestMethod.GET)`的缩写。该注解将HTTP Get 映射到 特定的处理方法上 |
  | `@PostMapping`                                    | 是`@RequestMapping(method = RequestMethod.POST)`的缩写。该注解用于将HTTP post请求映射到特定处理程序的方法注解 |
  | `@Slf4j`                                          | 引入日志                                                     |
  | `@Param`                                          | xml中的参数用在方法中使用注解。例如：`@Param(value = "condition") String condition` |
  | `@SpringBootApplication`                          | 是一个组合注解，它整合了`@Configuration`、`@EnableAutoConfiguration`和`@ComponentScan`注解，并开启了Spring Boot程序的组件扫描和自动配置功能。 |
  | `@Autowired`                                      | 自动装配，其作用是为了消除代码Java代码里面的getter/setter与bean属性中的property |

  

  * 加配置：application.yml（一般配置）：

```yaml
server:
  port: 8000
spring:
  jpa:
    # 让hibernate打印执行的SQL
    show-sql: true
logging:
  level:
    root: INFO
    # 配置日志级别，让hibernate打印出执行的SQL参数
    org.hibernate: INFO
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.hibernate.type.descriptor.sql.BasicExtractor: TRACE
```

### 二、Spring Boot的监控组件Spring Boot Actuator

#### 1、Actuator为我们提供了很多监控端点，如下表所示。

| 端点（Spring Boot 2.x） | 描述                                                         | HTTP方法 | 是否敏感 | 端点（Spring Boot 1.x） |
| :---------------------- | :----------------------------------------------------------- | :------- | :------- | :---------------------- |
| conditions              | 显示自动配置的信息                                           | GET      | 是       | autoconfig              |
| beans                   | 显示应用程序上下文所有的`Spring bean`                        | GET      | 是       | beans                   |
| configprops             | 显示所有`@ConfigurationProperties`的配置属性列表             | GET      | 是       | configprops             |
| dump                    | 显示线程活动的快照                                           | GET      | 是       | dump                    |
| env                     | 显示环境变量，包括系统环境变量以及应用环境变量               | GET      | 是       | env                     |
| health                  | 显示应用程序的健康指标，值由HealthIndicator的实现类提供；结果有UP、 DOWN、OUT_OF_SERVICE、UNKNOWN；如需查看详情，需配置：`management.endpoint.health.show-details` | GET      | 否       | health                  |
| info                    | 显示应用的信息，可使用`info.*` 属性自定义info端点公开的数据  | GET      | 否       | info                    |
| mappings                | 显示所有的URL路径                                            | GET      | 是       | mappings                |
| metrics                 | 显示应用的度量标准信息                                       | GET      | 是       | metrics                 |

只需访问`http://{ip}:{port}/actuator/{endpoint}` 端点，即可监控应用的运行状况。

#### 2、添加依赖：

```xml
<!--Spring Boot Actuator是Spring Boot官方提供的监控组件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

#### 3、加配置：

```yaml
management:
  endpoint:
    health:
      # 是否展示健康检查详情
      show-details: always
      beans:
        enabled: true
      env:
        enabled: true
      mappings:
        enabled: true
  endpoints:
    web:
      exposure:
        # 暴露metrics端点，如需暴露多个，用,分隔；如需暴露所有端点，用'*'
        include: '*'
```

### 三、服务注册与服务发现eureka

Eureka是Netflix开源的服务发现组件，本身是一个基于REST的服务，包含Server和Client两部分，Spring Cloud将它集成在子项目Spring Cloud Netflix中。

#### 1、Eureka原理

- Eureka Server提供服务发现的能力，各个微服务启动时，会向Eureka Server注册自己的信息（例如IP、端口、微服务名称等），Eureka Server会存储这些信息；

- Eureka Client是一个Java客户端，用于简化与Eureka Server的交互；

- 微服务启动后，会周期性（**默认30秒**）地向Eureka Server发送心跳以续约自己的“租期”；

- 如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（**默认90秒**）；
- 默认情况下，**Eureka Server同时也是Eureka Client。多个Eureka Server实例，互相之间通过增量复制的方式，来实现服务注册表中数据的同步**。Eureka Server默认保证在90秒内，Eureka Server集群内的所有实例中的数据达到一致（从这个架构来看，Eureka Server所有实例所处的角色都是**对等**的，没有类似Zookeeper、Consul、Etcd等软件的选举过程，也不存在主从，**所有的节点都是主节点**。Eureka官方将Eureka Server集群中的所有实例称为“**对等体（peer）**”）
- Eureka Client会缓存服务注册表中的信息。这种方式有一定的优势——首先，微服务无需每次请求都查询Eureka Server，从而降低了Eureka Server的压力；其次，即使Eureka Server所有节点都宕掉，服务消费者依然可以使用缓存中的信息找到服务提供者并完成调用。

综上，Eureka通过心跳检查、客户端缓存等机制，提高了系统的灵活性、可伸缩性和可用性。

#### 2、Eureka代码示例

##### 2.1、编写Eureka Server

（1）加依赖：

```xml
<dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

（2）启动类加注解：`@EnableEurekaServer`

（3）加配置：

```yaml
server:
  port: 8761
eureka:
  client:
    # 是否要注册到其他Eureka Server实例
    register-with-eureka: false
    # 是否要从其他Eureka Server实例获取数据
    fetch-registry: false
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

##### 2.2、编写Eureka Client，将应用注册到Eureka Server上

（1）加依赖：

```xml
<dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

（2）启动类加注解：早期的版本（Dalston及更早版本）还需在启动类上添加注解`@EnableDiscoveryClient` 或`@EnableEurekaClient` ，从Edgware开始，该注解可省略。

（3）加配置：

```yaml
spring:
  application:
    # 指定注册到eureka server上的服务名称
    name: user
eureka:
  client:
    service-url:
      # 指定eureka server通信地址，注意/eureka/小尾巴不能少
      defaultZone: http://localhost:8761/eureka/
  instance:
    # 是否注册IP到eureka server，如不指定或设为false，那就会注册主机名到eureka server
    prefer-ip-address: false
```

#### 3、高可用Eureka Server

##### Eureka Server配置：

```yaml
spring:
  application:
    name: eureka-ha
---
spring:
  profiles: peer1                                 # 指定profile=peer1
server:
  port: 8761
eureka:
  instance:
    hostname: peer1                               # 指定当profile=peer1时，主机名是peer1
  client:
    serviceUrl:
      defaultZone: http://peer2:8762/eureka/      # 将自己注册到peer2这个Eureka上面去
---
spring:
  profiles: peer2
server:
  port: 8762
eureka:
  instance:
    hostname: peer2
  client:
    serviceUrl:
      defaultZone: http://peer1:8761/eureka/

```

##### Eureka Client配置：

```yaml
spring:
  application:
    # 指定注册到eureka server上的服务名称
    name: user
eureka:
  client:
    service-url:
      defaultZone: http://peer1:8761/eureka/,http://peer2:8762/eureka/
  instance:
    # 是否注册IP到eureka server，如不指定或设为false，那就会注册主机名到eureka server
    prefer-ip-address: false
```

#### 4、Eureka安全认证

Eureka本身不具备安全认证的能力，Spring Cloud使用Spring Security为Eureka Server进行了增强。

##### 4.1 Eureka Server端

此处构建一个需要登录才能访问的Eureka Server。

（1）加依赖：

```xml
<dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-security</artifactId>
</dependency>
```

（2）启动类加注解：`@EnableEurekaServer`

（3）配置：

```yaml
server:
  port: 8761
eureka:
  client:
    # 是否要注册到其他Eureka Server实例
    register-with-eureka: false
    # 是否要从其他Eureka Server实例获取数据
    fetch-registry: false
    service-url:
    #加上用户名密码
      defaultZone: http://user:password1234@localhost:8761/eureka/
      
# 如不设置这段内容，账号默认是user，密码是一个随机值，该值会在启动时打印出来。
spring:
  security:
    user:
      # 配置登录的账号是user
      name: user
      # 配置登录的密码是123456
      password: 123456
```

（4）写代码：

```java
/**
 * Spring Cloud Finchley及更高版本，必须添加如下代码，部分关闭掉Spring Security
 * 的CSRF保护功能，否则应用无法正常注册！
 * ref: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_securing_the_eureka_server
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
```

（5）运行：http://localhost:8761

##### 4.2 Eureka Client端

改配置：

```yaml
eureka:
  client:
    service-url:
      # 指定eureka server通信地址，注意/eureka/小尾巴不能少
    defaultZone: http://user:123456@localhost:8761/eureka/
  instance:
    # 是否注册IP到eureka server，如不指定或设为false，那就会注册主机名到eureka server
    prefer-ip-address: false
```

*参考：*

> 周立---http://www.itmuch.com/spring-cloud/spring-cloud-index/

