package com.flcode.api_user_flcode.application.port.in;

import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.domain.model.UserResponse;
import com.flcode.api_user_flcode.infrastructure.model.UserRequest;
import reactor.core.publisher.Mono;

public interface UserImputPort {

    Mono<UserListResponse>findById(Integer id);
    Mono<UserListResponse>findAll();
    Mono<UserResponse>saveUser(UserRequest userRequest);
    Mono<UserResponse>updateUser(UserRequest userRequest);
    Mono<UserResponse>deleteById(Integer idUser);
}
