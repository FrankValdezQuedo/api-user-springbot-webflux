package com.flcode.api_user_flcode.domain.error;

import com.flcode.api_user_flcode.infrastructure.utils.Constantes;
import io.r2dbc.spi.R2dbcException;
import lombok.NoArgsConstructor;
import reactor.core.Exceptions;

@NoArgsConstructor
public class UserErrorFactory {
    public static RuntimeException createException(Throwable error) {
        if (error instanceof R2dbcException) {
            return new ServiceUnavailableExceptions(Constantes.DATABASE_UNAVAILABLE);
        }
        if (Exceptions.isRetryExhausted(error)) {
            return new GatewayTimeOutExceptions(Constantes.DATABASE_TIMEOUT);
        }
        if (error instanceof UserNotFoundException) {
            return new UserNotFoundException(error.getMessage());
        }
        return new UserExceptions(Constantes.DATABASE_USER_EXCEPTIONS);
    }
}
