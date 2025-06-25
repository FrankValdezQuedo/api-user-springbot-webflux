package com.flcode.api_user_flcode.application.port.out;

import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface AuthRepositoryOutputPort {
    Mono<UserEntity> findByEmail(String email);
}
