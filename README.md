## 一、微服务架构

#### 1、单体架构的问题

- 复杂性高
- 技术债务
- 部署频率低
- 可靠性差
- 扩展能力受限

#### 2、什么是微服务

- 每个微服务可独立运行在自己的进程
- 一系列独立运行的微服务共同构建整个系统
- 每个微服务只关注某个特定的功能
- 微服务之间通过轻量的通信机制进行通信
- 可以使用不同的语言和数据存储
- 全自动部署

#### 3、优点和缺点

##### 优点：

- 易于开发和维护
- 单个微服务启动较快
- 局部修改容易部署
- 技术栈不受限制
- 按需伸缩


##### 挑战：

- 运维要求较高
- 分布式固有的复杂性
- 接口调整成本较高
- 重复劳动

#### 4、技术选型

> 可以使用SpringCloud，也可以使用Dubbo
 
## 二、SpringCloud

#### 1、特点

- 约定优于配置
- 适用于各种环境
- 隐藏了组件的复杂性
- 开箱即用
- 轻量级的组件
- 组件丰富

#### 2、版本

>  SpringCloud是一个综合项目，是以英文单词SRX（X为数字）的形式命名版本号。Angel、Brixton、Camden等都是伦敦地铁站的名字，他们按照首字母顺序发行，可看作主版本的演进。

> SR表示“Service Release”，表示bug修复；在SR版本发发布之前，会先发布一个Release版本，例如：Camden RELEASE。

#### 3、SpringCloud与SpringBoot版本兼容性

> Angel版本基于SpringBoot 1.2构建；

> Brixton版本基于SpringBoot 1.3构建；

> Camden版本基于SpringBoot 1.4构建；

可以在[官网](http://projects.spring.io/spring-cloud/)查看版本兼容性

## 三、基于SpringCloud微服务实战

#### 1、基于Eureka实现微服务的注册与发现

- 创建eureka-server项目
```
<dependencies>
        <!-- spring cloud start -->
        <!-- Eureka Server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
        <!-- config -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!-- spring cloud end -->

        <!-- Spring Boot start -->
        <!-- security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <!-- test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot end -->

    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Dalston.SR5</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
```
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```
```
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    # 是否注册到Eureka服务中
    register-with-eureka: false
    # 是否同步其他Eureka节点的信息
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
- 将微服务注册在Eureka中
- 创建项目movie-consumer-user
```
server:
  port: 8010
spring:
  application:
    name: movie-consumer-user
eureka:
  client:
    service-url:
      defaultZone: http://@peer1:8761/eureka/
  instance:
    prefer-ip-address: true
```
```
<dependencies>
        <!-- spring boot start-->
        <!-- starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <!-- test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- spring boot end-->

        <!-- spring cloud start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <!-- spring cloud end -->
        <!-- lombok start -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.20</version>
            <scope>provided</scope>
        </dependency>
        <!-- lombok start -->

    </dependencies>
```
```
@EnableEurekaClient
@SpringBootApplication
public class MovieConsumerUserApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerUserApplication.class, args);
    }
}
```
- Eureka服务的高可用
- 创建eureka-server-ha项目
```
spring:
  application:
    name: eureka-server-ha
---
spring:
  profiles: peer1
server:
  port: 8761
eureka:
  instance:
    hostname: peer1
  client:
    service-url:
      defaultZone: http://peer2:8762/eureka/
---
spring:
  profiles: peer2
server:
  port: 8762
eureka:
  instance:
    hostname: peer2
  client:
    service-url:
      defaultZone: http://peer1:8761/eureka/

```
```
@EnableEurekaClient
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerHaApplication.class, args);
    }
}
```
- 微服务注册在Eureka集群
- 修改微服务movie-consumer-user
```
eureka:
  client:
    service-url:
      defaultZone: http://peer1:8761/eureka/,http://peer2:8762/eureka/
```
- Eureka添加用户认证
- 修改eureka-server
```
<!-- security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
```
security:
  basic:
    enabled: true
  user:
    name: user
    password: user
```
- 微服务添加元数据
- 修改movie-provider-user
```
eureka:
  client:
    service-url:
      defaultZone: http://user:user@peer1:8761/eureka/
  instance:
    prefer-ip-address: true
    metadata-map:
      my-metadata: my-metadata
```
- 微服务访问元数据
- 修改movie-consumer-user，在controller中添加以下代码
```
@Autowired
private DiscoveryClient discoveryClient;

@GetMapping("/user-instance")
public List<ServiceInstance> showInfo() {
    return discoveryClient.getInstances("movie-provider-user");
}
```
- Eureka的自我保护
> 默认情况下，Eureka在一定时间（默认90秒）内，没有接收到某个微服务实例的心跳，Eureka会将该实例注销。但是当发生故障时，微服务与Eureka之间无法正常通信，此时不应该注销该服务。

> Eureka通过“自我保护模式”来解决这个问题，当Eureka节点在短时间丢失过多客户端时，这个节点会进入自我保护模式，保护服务注册表中的信息，不再删除服务注册表中的微服务实例，当故障恢复时，该节点会自动退出自我保护。


#### 2、基于Ribbon实现客户端侧负载均衡

> Ribbon是Netflix发布的负载均衡器，有助于控制HTTP和TCP客户端的行为。

- 为movie-consumer-user引入Ribbon，因为Eureka本身包含Ribbon所以无须再次引入
- 修改项目movie-consumer-user

```
@Slf4j
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        ResponseEntity<User> response =
            restTemplate.getForEntity("http://movie-provider-user/user/" + id, User.class);
        return response.getBody();
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("movie-provider-user");
    }

    @GetMapping("/log-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("movie-provider-user");
        log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }
}
```
- 添加配置类

```
/**
 * Ribbon配置类
 * PS：该类不应该在主应用程序上下文的@ComponentScan中
 *
 * @author Steven
 * @date 2018/9/26 026 14:36
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        // 负载规则为随机
        return new RandomRule();
    }
}
```



#### 3、基于Fegin实现声明式REST调用

#### 4、基于Hystrix实现微服务的容错管理

#### 5、基于Zuul构建微服务网关

#### 6、使用config统一管理微服务配置

#### 7、使用Sleuth实现微服务跟踪

## 四、SpringCloud常见问题

## 五、与Docker的集成
