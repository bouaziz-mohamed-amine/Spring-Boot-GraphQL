package com.example.graph.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilter {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").hasAnyRole("ADMIN","USER") // Protect /graphql
                        .requestMatchers("/rest/auth/login").permitAll() // Login is public
                        .requestMatchers("/rest/auth/register").permitAll() // Register is public
                        .anyRequest().authenticated() // everything else requires login
                )
                .httpBasic(Customizer.withDefaults()); // Use basic auth (username/password)
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}