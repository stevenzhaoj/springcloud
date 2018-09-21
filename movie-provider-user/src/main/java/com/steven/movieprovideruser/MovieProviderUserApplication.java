package com.steven.movieprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Steven
 * @since 2018年9月21日 15:00:44
 */
@EnableEurekaClient
@SpringBootApplication
public class MovieProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProviderUserApplication.class, args);
    }
}
