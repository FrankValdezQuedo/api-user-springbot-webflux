package com.flcode.api_user_flcode.domain.error;

public class GatewayTimeOutExceptions extends RuntimeException {
    public GatewayTimeOutExceptions(String message) {
        super(message);
    }
}
