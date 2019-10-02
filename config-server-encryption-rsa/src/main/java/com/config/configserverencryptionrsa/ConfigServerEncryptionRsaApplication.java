package com.config.configserverencryptionrsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerEncryptionRsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerEncryptionRsaApplication.class, args);
    }

}
