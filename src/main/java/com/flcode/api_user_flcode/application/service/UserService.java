package com.flcode.api_user_flcode.application.service;

import com.flcode.api_user_flcode.application.port.in.UserImputPort;
import com.flcode.api_user_flcode.application.port.out.UserRepositoryOutputPort;
import com.flcode.api_user_flcode.domain.error.UserNotFoundException;
import com.flcode.api_user_flcode.domain.model.LoginResponse;
import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.domain.model.UserResponse;
import com.flcode.api_user_flcode.infrastructure.model.UserRequest;
import com.flcode.api_user_flcode.infrastructure.utils.Constantes;
import com.flcode.api_user_flcode.infrastructure.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserImputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    @Override
    public Mono<UserListResponse> findById(Integer id) {
        return userRepositoryOutputPort
                .findById(id)
                .map(UserUtils::convertUserSingletonResponse)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado")))
                .doOnError(error -> log.error(error.getMessage()))
                .onErrorResume(UserUtils::handleErrorUserMono);
    }

    @Override
    public Mono<UserListResponse> findAll() {
        return userRepositoryOutputPort
                .findAll()
                .collectList()
                .map(UserUtils::convertUserListResponse)
                .doOnError(error -> log.error(error.getMessage()))
                .onErrorResume(UserUtils::handleErrorUserMono);

    }


    @Override
    public Mono<UserResponse> saveUser(UserRequest userRequest) {
        return Mono.just(userRequest)
                .map(user -> UserUtils.convertUserEntity(userRequest))
                .flatMap(userRepositoryOutputPort::saveOrUpdate)
                .map(x -> UserUtils.convertUserResponseSave(String.valueOf(x.getId())))
                .doOnError(x -> log.error(Constantes.ERROR_SAVE + "{}", x.getMessage()))
                .onErrorResume(UserUtils::handleErrorUser);
    }


    @Override
    public Mono<UserResponse> updateUser(UserRequest userRequest) {
        return userRepositoryOutputPort
                .findById(userRequest.getIdUser())
                .map(existingUser -> UserUtils.convertUserUpdateEntity(userRequest))
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado con ID: " + userRequest.getIdUser())))
                .flatMap(userRepositoryOutputPort::saveOrUpdate)
                .map(x -> UserUtils.convertUserResponseSave(String.valueOf(x.getId())))
                .doOnError(x -> log.error(Constantes.ERROR_UPDATE + "{}", String.valueOf(x)))
                .onErrorResume(UserUtils::handleErrorUser);
    }

    @Override
    public Mono<UserResponse> deleteById(Integer idUser) {
        return userRepositoryOutputPort
                .findById(idUser)
                .flatMap(userExisting -> {
                    return userRepositoryOutputPort
                            .deleteById(idUser)
                            .then(
                                    Mono.defer(() -> Mono.just(UserUtils.convertUserResponseDelete(idUser))));
                })
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado con ID: " + idUser)))
                .doOnError(error -> System.out.println(Constantes.ERROR_DELETE + error))
                .onErrorResume(UserUtils::handleErrorUser);
    }

    @Override
    public Mono<LoginResponse> login(String email, String password) {
        return userRepositoryOutputPort.findByEmailAndPassword(email, password)
                .map(UserUtils::convertLoginResponse)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado con email: " + email)))
                .doOnError(error -> log.error(error.getMessage()))
                .onErrorResume(UserUtils::handleErrorLoginMono);
    }
}
