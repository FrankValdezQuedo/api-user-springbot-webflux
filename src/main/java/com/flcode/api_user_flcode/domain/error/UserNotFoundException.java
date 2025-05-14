package com.flcode.api_user_flcode.domain.error;

public class UserNotFoundException  extends  RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
