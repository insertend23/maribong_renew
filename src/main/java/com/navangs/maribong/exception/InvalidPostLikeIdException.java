package com.navangs.maribong.exception;

public class InvalidPostLikeIdException extends RuntimeException {
    public static final String INVALID_LIKE_ID_MESSAGE = "설정된 좋아요가 없습니다.";

    public InvalidPostLikeIdException() {
        super(INVALID_LIKE_ID_MESSAGE);
    }
}
