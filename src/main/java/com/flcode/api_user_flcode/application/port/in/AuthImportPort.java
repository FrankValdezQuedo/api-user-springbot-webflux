package com.flcode.api_user_flcode.application.port.in;

import com.flcode.api_user_flcode.domain.model.LoginResponse;
import reactor.core.publisher.Mono;

public interface AuthImportPort {
    Mono<LoginResponse> login(String email, String password);

}
