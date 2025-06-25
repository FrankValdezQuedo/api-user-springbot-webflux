package com.flcode.api_user_flcode.infrastructure.Adapter;

import com.flcode.api_user_flcode.application.port.out.AuthRepositoryOutputPort;
import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import com.flcode.api_user_flcode.infrastructure.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthAdapter implements AuthRepositoryOutputPort {

    private final AuthRepository authRepository;
    @Override
    public Mono<UserEntity> findByEmail(String email) {
        return authRepository.findByEmail(email);
    }
}
