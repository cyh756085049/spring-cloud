package com.consumer.moviefeignconfigproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieFeignConfigPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieFeignConfigPropertiesApplication.class, args);
    }

}
