package com.flcode.api_user_flcode.infrastructure.utils;

import com.flcode.api_user_flcode.domain.error.UserErrorFactory;
import com.flcode.api_user_flcode.domain.model.User;
import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.domain.model.UserResponse;
import com.flcode.api_user_flcode.infrastructure.entity.UserEntity;
import com.flcode.api_user_flcode.infrastructure.model.UserRequest;
import reactor.core.publisher.Mono;

import java.util.List;


import java.util.Collections;

public class UserUtils {

    public static UserListResponse convertUserSingletonResponse(UserEntity entity) {
        return UserListResponse.builder()
                .data(Collections.singletonList(convertUserResponse(entity)))
                .build();

    }

    public static User convertUserResponse(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fechaNacimiento(entity.getFechaNacimiento())
                .telefono(entity.getTelefono())
                .direccion(entity.getDireccion())
                .ciudad(entity.getCiudad())
                .pais(entity.getPais())
                .codigoPostal(entity.getCodigoPostal())
                .activo(entity.getActivo())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaActualizacion(entity.getFechaActualizacion())
                .build();
    }

    public static UserEntity convertUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .nombre(userRequest.getNombre())
                .apellido(userRequest.getApellido())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .fechaNacimiento(userRequest.getFechaNacimiento())
                .telefono(userRequest.getTelefono())
                .direccion(userRequest.getDireccion())
                .ciudad(userRequest.getCiudad())
                .pais(userRequest.getPais())
                .codigoPostal(userRequest.getCodigoPostal())
                .activo(userRequest.getActivo())
                .fechaCreacion(userRequest.getFechaCreacion())
                .fechaActualizacion(userRequest.getFechaActualizacion())
                .build();
    }

    public static UserEntity convertUserUpdateEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .id(userRequest.getIdUser())
                .nombre(userRequest.getNombre())
                .apellido(userRequest.getApellido())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .fechaNacimiento(userRequest.getFechaNacimiento())
                .telefono(userRequest.getTelefono())
                .direccion(userRequest.getDireccion())
                .ciudad(userRequest.getCiudad())
                .pais(userRequest.getPais())
                .codigoPostal(userRequest.getCodigoPostal())
                .activo(userRequest.getActivo())
                .fechaCreacion(userRequest.getFechaCreacion())
                .fechaActualizacion(userRequest.getFechaActualizacion())
                .build();
    }

    public static UserResponse convertUserResponseSave(String idUser) {
        return UserResponse.builder()
                .codResponse(Constantes.COD_RESPONSE)
                .messageResponse(Constantes.USER_SAVED)
                .codEntity(idUser)
                .build();
    }


    public static UserResponse convertUserResponseDelete(Integer idUser) {
        return UserResponse.builder()
                .codResponse(Constantes.COD_RESPONSE)
                .messageResponse(Constantes.USER_DELETED)
                .build();
    }

    public static UserListResponse convertUserListResponse(List<UserEntity> entity) {
        return UserListResponse.builder()
                .data(entity.stream()
                        .map(UserUtils::convertUserResponse)
                        .toList())
                .build();
    }

    public static Mono<UserListResponse> handleErrorUserMono(Throwable error) {
        return Mono.error(UserErrorFactory.createException(error));
    }

    public static Mono<UserResponse> handleErrorUser(Throwable error) {
        return Mono.error(UserErrorFactory.createException(error));
    }
}
