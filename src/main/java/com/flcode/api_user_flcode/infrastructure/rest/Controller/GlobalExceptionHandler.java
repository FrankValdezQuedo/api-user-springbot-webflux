package com.flcode.api_user_flcode.infrastructure.rest.Controller;

import com.flcode.api_user_flcode.domain.error.GatewayTimeOutExceptions;
import com.flcode.api_user_flcode.domain.error.ServiceUnavailableExceptions;
import com.flcode.api_user_flcode.domain.error.UserExceptions;
import com.flcode.api_user_flcode.domain.error.UserNotFoundException;
import com.flcode.api_user_flcode.domain.model.ErrorResponse;
import com.flcode.api_user_flcode.domain.model.Notification;
import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.infrastructure.configuration.ExceptionProperties;
import com.flcode.api_user_flcode.infrastructure.utils.Constantes;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionProperties exceptionProperties;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorResponse handleWebExchangeBindException(WebExchangeBindException ex) {
        String errorMessage =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .findFirst()
                        .orElse(Constantes.INVALID_REQUEST);

        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(HttpStatus.BAD_REQUEST.name())
                                        .message(errorMessage)
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(Constantes.BAD_REQUEST)
                                        .build()))
                .build();
    }

    // 400 valida resquest
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .findFirst()
                        .orElse(Constantes.INVALID_REQUEST);

        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(HttpStatus.BAD_REQUEST.name())
                                        .message(errorMessage)
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(Constantes.BAD_REQUEST)
                                        .build()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ErrorResponse handlerValidationException1(Exception ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(HttpStatus.BAD_REQUEST.name())
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .build()))
                .build();
    }

    // Manejo de errores generales - Código 500
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerWebInputException.class)
    public ErrorResponse handlerSer(ServerWebInputException ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(Constantes.BAD_REQUEST)
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(HttpStatus.BAD_REQUEST.name())
                                        .build()))
                .build();
    }

    // Manejo de errores generales - Código 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerException(Exception ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(Constantes.INTERNAL_ERROR_CATEGORY)
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                        .build()))
                .build();
    }

    // Manejo de errores generales - Código 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserExceptions.class)
    public ErrorResponse handlerSupplierExceptions(UserExceptions ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(Constantes.INTERNAL_ERROR_CATEGORY)
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                        .build()))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserListResponse handlerSupplierNotFoundException(UserNotFoundException ex) {
        return UserListResponse
                .builder()
                .errorResponse(ErrorResponse.
                        builder()
                        .notifications(List.of(Notification.builder()
                                .code(Constantes.NOT_FOUND)
                                .message(ex.getMessage())
                                .uuid(UUID.randomUUID().toString())
                                .timestamp(LocalDate.now().toString())
                                .severity(Constantes.SEVERITY)
                                .category(HttpStatus.NOT_FOUND.name())
                                .build()))
                        .build())
                .build();
    }
    // Manejo de errores generales - Código 503
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceUnavailableExceptions.class)
    public ErrorResponse handlerSupplierExceptions(ServiceUnavailableExceptions ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(Constantes.SERVICE_UNAVAILABLE)
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(HttpStatus.SERVICE_UNAVAILABLE.name())
                                        .build()))
                .build();
    }

    // Manejo de errores generales - Código 504
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(GatewayTimeOutExceptions.class)
    public ErrorResponse handlerSupplierExceptions(GatewayTimeOutExceptions ex) {
        return ErrorResponse.builder()
                .notifications(
                        List.of(
                                Notification.builder()
                                        .code(Constantes.GATEWAY_TIMEOUT)
                                        .message(ex.getMessage())
                                        .uuid(UUID.randomUUID().toString())
                                        .timestamp(LocalDate.now().toString())
                                        .severity(Constantes.SEVERITY)
                                        .category(HttpStatus.GATEWAY_TIMEOUT.name())
                                        .build()))
                .build();
    }
}
