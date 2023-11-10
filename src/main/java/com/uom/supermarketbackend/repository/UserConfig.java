package com.uom.supermarketbackend.repository;

import com.uom.supermarketbackend.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository users)
    {
        return args -> {
            User yukthi= new User(1L,null);
            User nuwan= new User(2L,null);

            users.saveAll(List.of(yukthi,nuwan));
        };

    }
}
