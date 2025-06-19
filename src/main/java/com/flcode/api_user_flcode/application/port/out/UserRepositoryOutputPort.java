package com.flcode.api_user_flcode.application.port.out;
import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


public interface UserRepositoryOutputPort {
    Mono<UserEntity> findById(Integer id);

    Flux<UserEntity> findAll();

    Mono<UserEntity> saveOrUpdate(UserEntity userEntity);

    Mono<Void> deleteById(Integer idUser);

    Mono<UserEntity> findByEmail(String email);
}

