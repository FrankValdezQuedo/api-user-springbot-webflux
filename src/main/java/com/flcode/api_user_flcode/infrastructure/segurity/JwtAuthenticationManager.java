package com.flcode.api_user_flcode.infrastructure.segurity;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {


    public JwtAuthenticationManager(JwtUtil jwtUtil) {
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        if (!JwtUtil.validateToken(authToken)) {
            return Mono.empty(); // token inválido
        }

        String userId = JwtUtil.getUserId(authToken);
        return Mono.just(new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList()));
    }
}
