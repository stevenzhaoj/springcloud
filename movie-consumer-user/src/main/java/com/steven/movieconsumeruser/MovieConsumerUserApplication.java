package com.steven.movieconsumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Steven
 * @date 2018年9月21日 15:56:54
 */
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
