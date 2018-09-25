package com.steven.movieconsumeruser.controller;

import com.steven.movieconsumeruser.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Steven
 * @since 2018年9月21日 14:57:24
 */
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
            restTemplate.getForEntity("http://localhost:8000/user/" + id, User.class);
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
