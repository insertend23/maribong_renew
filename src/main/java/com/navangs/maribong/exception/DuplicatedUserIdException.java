package com.navangs.maribong.exception;

public class DuplicatedUserIdException extends MaribongException {
    private static final String USER_ID_DUPLICATED_MSG = "아이디가 중복되었습니다.";

    public DuplicatedUserIdException() {
        super(USER_ID_DUPLICATED_MSG);
    }
}
