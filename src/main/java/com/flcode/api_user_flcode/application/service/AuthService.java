package com.flcode.api_user_flcode.application.service;

import com.flcode.api_user_flcode.application.port.in.AuthImportPort;
import com.flcode.api_user_flcode.application.port.out.AuthRepositoryOutputPort;
import com.flcode.api_user_flcode.domain.error.InvalidCredentialsException;
import com.flcode.api_user_flcode.domain.error.UserNotFoundException;
import com.flcode.api_user_flcode.domain.model.LoginResponse;
import com.flcode.api_user_flcode.infrastructure.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService implements AuthImportPort {

    private final AuthRepositoryOutputPort authRepositoryOutputPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<LoginResponse> login(String email, String rawPassword) {
        return authRepositoryOutputPort.findByEmail(email)
                .doOnNext(user -> log.info("Usuario encontrado: {}", user.getEmail()))
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado con email: " + email)))
                .flatMap(user -> {
                    log.debug("Raw password: {}", rawPassword);
                    log.debug("Stored hash: {}", user.getPassword());

                    if (user.getPassword() == null) {
                        return Mono.error(new IllegalStateException("Contraseña no encontrada en usuario"));
                    }

                    boolean passwordValid = passwordEncoder.matches(rawPassword, user.getPassword());
                    if (passwordValid) {
                        return Mono.just(UserUtils.convertLoginResponse(user));
                    } else {
                        return Mono.error(new InvalidCredentialsException("Contraseña incorrecta"));
                    }
                })
                .doOnError(error -> log.error("Error en login", error))
                .onErrorResume(UserUtils::handleErrorLoginMono);
    }
}
