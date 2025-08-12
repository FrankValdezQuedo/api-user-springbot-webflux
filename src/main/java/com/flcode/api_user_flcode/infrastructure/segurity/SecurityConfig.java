package com.flcode.api_user_flcode.infrastructure.segurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // ⬅️ Desactiva Basic Auth
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // ⬅️ Desactiva Form Login
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/user/save", "/api/user/login","/api/user/all", "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml").permitAll()
                        .anyExchange().authenticated()
                )
                .authenticationManager(new JwtAuthenticationManager(jwtUtil))
                .securityContextRepository(new JwtSecurityContextRepository(jwtUtil))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
