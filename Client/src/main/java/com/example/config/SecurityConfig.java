package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/assets/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
        , ClientRegistrationRepository clientRegistrationRepository
    ) throws Exception {
        http
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/logged-out").permitAll()
                    .anyRequest().authenticated())
            .oauth2Login(oauth2Login->
                oauth2Login
                    .loginPage("/oauth2/authorization/messaging-client-oidc"))
            .oauth2Client(Customizer.withDefaults())
//            .logout(logout->logout.logoutSuccessHandler(new ForwardLogoutSuccessHandler("/logged-out")))
            .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)))
        ;
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository){
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
            new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logged-out");
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://127.0.0.1:8080/logged-out");
        return oidcLogoutSuccessHandler;
    }
}
