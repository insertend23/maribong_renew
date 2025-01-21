package com.navangs.maribong.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(String message) {
        super(message);
    }
}
