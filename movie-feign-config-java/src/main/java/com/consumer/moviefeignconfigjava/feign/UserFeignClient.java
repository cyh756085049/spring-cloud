package com.consumer.moviefeignconfigjava.feign;

import com.consumer.moviefeignconfigjava.entity.User;
import com.consumer.moviefeignconfigjava.logger.UserFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", configuration = UserFeignConfig.class)
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);
}

