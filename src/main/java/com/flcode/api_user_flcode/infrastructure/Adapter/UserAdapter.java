package com.flcode.api_user_flcode.infrastructure.Adapter;

import com.flcode.api_user_flcode.application.port.out.UserRepositoryOutputPort;
import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import com.flcode.api_user_flcode.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserRepositoryOutputPort {

    private final UserRepository userRepository;

    @Override
    public Mono<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserEntity> saveOrUpdate(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Mono<Void> deleteById(Integer idUser) {
        return userRepository.deleteById(idUser);
    }
}
