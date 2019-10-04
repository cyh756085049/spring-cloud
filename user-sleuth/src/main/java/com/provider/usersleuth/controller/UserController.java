package com.provider.usersleuth.controller;

import com.provider.usersleuth.dao.UserRepository;
import com.provider.usersleuth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    //@PathVariable是用来获得请求url中的动态参数的
    public Optional<User> findById(@PathVariable Long id){
        return this.userRepository.findById(id);
    }
}
