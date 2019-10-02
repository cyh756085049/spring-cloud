package com.config.configserverencryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerEncryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerEncryptionApplication.class, args);
    }

}
