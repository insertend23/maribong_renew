package com.navangs.maribong.exception;

public class UserPasswordIncorrectException extends MaribongException {
    private static final String PASSWORD_INCORRECT_MESSAGE = "비밀번호가 다릅니다.";

    public UserPasswordIncorrectException() {
        super(PASSWORD_INCORRECT_MESSAGE);
    }
}
