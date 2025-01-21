package com.navangs.maribong.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedUserIdException extends RuntimeException {
    public DuplicatedUserIdException(String message) {
        super(message);
    }
}
