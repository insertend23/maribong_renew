package com.navangs.maribong.exception;

public class UserIdNotFoundException extends MaribongException {
    private static final String USER_ID_NOT_FOUND_MESSAGE = "등록되지 않은 사용자입니다.";

    public UserIdNotFoundException() {
        super(USER_ID_NOT_FOUND_MESSAGE);
    }
}
