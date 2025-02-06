package com.navangs.maribong.exception;

public class InvalidReplyIdException extends RuntimeException {
    private static final String INVALID_REPLY_ID_MESSAGE = "댓글이 존재하지 않습니다.";

    public InvalidReplyIdException() {
        super(INVALID_REPLY_ID_MESSAGE);
    }
}
