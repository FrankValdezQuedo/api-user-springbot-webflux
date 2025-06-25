package com.flcode.api_user_flcode.infrastructure.repository;

import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthRepository extends ReactiveCrudRepository<UserEntity, String> {
    Mono<UserEntity> findByEmail(String email);

}
