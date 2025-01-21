package com.navangs.maribong.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException(String message) {
        super(message);
    }
}
