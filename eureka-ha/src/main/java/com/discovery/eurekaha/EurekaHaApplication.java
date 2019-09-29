package com.discovery.eurekaha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHaApplication.class, args);
    }

}
