package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DefaultAuthorizationServer {

    public static void main(String[] args) {
        SpringApplication.run(DefaultAuthorizationServer.class, args);
    }

//    @Bean
//    public UserDetailsService user1(){
//        UserDetails userDetails = User
//            .withUsername("user1")
//            .password("{noop}password") // The password would have a PasswordEncoder id of noop and encodedPassword of password. When matching it would delegate to NoOpPasswordEncoder
//                                        // Refer to https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html
//            .build();
////        System.out.println(userDetails.getPassword());
//        return new InMemoryUserDetailsManager(userDetails);
//    }

}
