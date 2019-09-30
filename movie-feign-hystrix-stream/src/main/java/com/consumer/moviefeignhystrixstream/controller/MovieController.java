package com.consumer.moviefeignhystrixstream.controller;

import com.consumer.moviefeignhystrixstream.entity.User;
import com.consumer.moviefeignhystrixstream.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id){
        User user = this.userFeignClient.findById(id);
        return user;
    }
}
