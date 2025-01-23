package com.navangs.maribong.exception;

public class FileTransferFailedException extends MaribongException {
    private static final String FILE_TRANSFER_FAILED_MESSAGE = "파일 전송에 실패하였습니다.";
    
    public FileTransferFailedException() {
        super(FILE_TRANSFER_FAILED_MESSAGE);
    }
}
