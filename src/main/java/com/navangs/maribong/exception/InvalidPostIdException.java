package com.navangs.maribong.exception;

public class InvalidPostIdException extends MaribongException {
    private static final String INVALID_POST_ID_MESSAGE = "게시글이 존재하지 않습니다.";

    public InvalidPostIdException() {
        super(INVALID_POST_ID_MESSAGE);
    }
}
