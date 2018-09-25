package com.steven.movieconsumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 1.添加@LoadBalanced就可以开启Ribbon负载均衡
 *
 * @author Steven
 * @date 2018年9月21日 15:56:54
 */
@EnableEurekaClient
@SpringBootApplication
public class MovieConsumerUserApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerUserApplication.class, args);
    }
}
