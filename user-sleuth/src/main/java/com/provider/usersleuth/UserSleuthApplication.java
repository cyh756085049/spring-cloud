package com.provider.usersleuth;

import com.provider.usersleuth.dao.UserRepository;
import com.provider.usersleuth.entity.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SpringBootApplication
public class UserSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSleuthApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserRepository userRepository){
        return args -> {
            User user1 = new User(1L, "account1", "张三", 20, new BigDecimal(100.00));
            User user2 = new User(2L, "account2", "李四", 28, new BigDecimal(180.00));
            User user3 = new User(3L, "account3", "王五", 32, new BigDecimal(280.00));
            Stream.of(user1,user2,user3).forEach(userRepository::save);
        };
    }
}
