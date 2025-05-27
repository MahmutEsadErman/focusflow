package org.focusflow.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF deaktivieren
                .formLogin(AbstractHttpConfigurer::disable) // Login-Formular deaktivieren// 👈 wichtig: Standard Login-Seite deaktivieren
                .httpBasic(AbstractHttpConfigurer::disable) // 👈 optional: auch Basic-Auth deaktivieren
                .authorizeHttpRequests(auth -> auth
                        //  .requestMatchers("/auth/**", "/register", "/login", "/public/**").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}