package com.consumer.moviefeignhystrixfallbackfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieFeignHystrixFallbackFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieFeignHystrixFallbackFactoryApplication.class, args);
    }

}
