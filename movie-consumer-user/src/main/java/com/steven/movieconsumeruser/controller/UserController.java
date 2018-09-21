package com.steven.movieconsumeruser.controller;

import com.steven.movieconsumeruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Steven
 * @since 2018年9月21日 14:57:24
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        ResponseEntity<User> response =
            restTemplate.getForEntity("http://localhost:8000/user/" + id, User.class);
        return response.getBody();
    }
}
