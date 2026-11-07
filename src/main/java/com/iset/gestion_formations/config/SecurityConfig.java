package com.iset.gestion_formations.config;


import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // 1. Security rules for HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Public pages and static resources
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**",
                    "/favicon.ico"
                ).permitAll()

                // User administration
                .requestMatchers("/utilisateurs/**")
                    .hasRole("ADMIN")

                // Existing business pages
                // Temporary rule: authenticated users only
                .requestMatchers(
                    "/themes/**",
                    "/sessions/**",
                    "/apprenants/**",
                    "/inscriptions/**",
                    "/formations/**"
                ).authenticated()

                // Protect all remaining pages
                .anyRequest().authenticated()
            )

            // 2. Custom login form
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", false)
                .failureUrl("/login?error")
                .permitAll()
            )

            // 3. Logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // 4. Access denied
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/403")
            );

        // CSRF remains enabled by default
        return http.build();
    }

    // 5. Password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

