package com.consumer.moviefeignconfigjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieFeignConfigJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieFeignConfigJavaApplication.class, args);
    }

}
