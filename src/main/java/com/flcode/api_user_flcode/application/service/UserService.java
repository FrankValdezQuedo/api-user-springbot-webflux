package com.flcode.api_user_flcode.application.service;

import com.flcode.api_user_flcode.application.port.in.UserImputPort;
import com.flcode.api_user_flcode.application.port.out.UserRepositoryOutputPort;
import com.flcode.api_user_flcode.domain.error.InvalidCredentialsException;
import com.flcode.api_user_flcode.domain.error.UserNotFoundException;
import com.flcode.api_user_flcode.domain.model.LoginResponse;
import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.domain.model.UserResponse;
import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import com.flcode.api_user_flcode.infrastructure.model.UserRequest;
import com.flcode.api_user_flcode.infrastructure.segurity.SecurityConfig;
import com.flcode.api_user_flcode.infrastructure.utils.Constantes;
import com.flcode.api_user_flcode.infrastructure.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserImputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;
    private final PasswordEncoder passwordEncoder;

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
        return Mono.fromSupplier(() -> {
                    // Convertir DTO a entidad
                    UserEntity userEntity = UserUtils.convertUserEntity(userRequest);
                    // Cifrar contraseña
                    userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    return userEntity;
                })
                .flatMap(userRepositoryOutputPort::saveOrUpdate)
                .map(user -> UserUtils.convertUserResponseSave(String.valueOf(user.getId())))
                .doOnError(error -> log.error(Constantes.ERROR_SAVE + "{}", error.getMessage()))
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
    public Mono<LoginResponse> login(String email, String rawPassword) {
        return userRepositoryOutputPort.findByEmail(email)
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
