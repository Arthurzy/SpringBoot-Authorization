package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@SpringBootApplication
public class AuthorizationServer {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServer.class, args);
    }

    @Bean
    public UserDetailsService user1 (){
        UserDetails user1 = User
            .withUsername("user1")
            .password("password")
            .passwordEncoder(passwordEncoder -> passwordEncoder.toLowerCase())
            .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
