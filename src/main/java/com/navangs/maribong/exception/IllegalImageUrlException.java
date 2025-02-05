package com.navangs.maribong.exception;

public class IllegalImageUrlException extends MaribongException {
    private static final String ILLEGAL_IMAGE_URL_MESSAGE = "잘못된 이미지 요청입니다.";

    public IllegalImageUrlException() {
        super(ILLEGAL_IMAGE_URL_MESSAGE);
    }
}
