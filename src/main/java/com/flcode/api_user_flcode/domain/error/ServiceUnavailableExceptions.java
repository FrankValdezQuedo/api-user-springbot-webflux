package com.flcode.api_user_flcode.domain.error;

public class ServiceUnavailableExceptions extends RuntimeException {
    public ServiceUnavailableExceptions(String message) {
        super(message);
    }
}
