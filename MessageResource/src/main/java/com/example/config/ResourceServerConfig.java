package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.securityMatcher("/messages/**")
//                .authorizeHttpRequests()
//                .requestMatchers("/messages/**")
//                .hasAuthority("SCOPE_message.read")
//               .and()
//                .oauth2ResourceServer()
//                .jwt();
        http
            .securityMatcher("/messages/**")
            .authorizeHttpRequests((authorize)->authorize
                .requestMatchers("/messages/**")
                .hasAuthority("SCOPE_message.read")
            )
            .oauth2ResourceServer((oauth2)-> oauth2
                .jwt(Customizer.withDefaults())
            );
        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("http://localhost:9000");
//    }
}
