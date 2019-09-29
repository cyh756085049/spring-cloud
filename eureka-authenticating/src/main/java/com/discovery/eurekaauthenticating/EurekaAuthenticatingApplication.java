package com.discovery.eurekaauthenticating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAuthenticatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAuthenticatingApplication.class, args);
    }

}
