package com.navangs.maribong.exception;

import com.navangs.maribong.response.BaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicatedUserIdException.class)
    private ResponseEntity<BaseResponse> handleDuplicatedUserIdException(DuplicatedUserIdException e) {
        return new ResponseEntity<>(new BaseResponse("101"), HttpStatus.OK);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    private ResponseEntity<BaseResponse> handleUserIdNotFoundException(UserIdNotFoundException e) {
        return new ResponseEntity<>(new BaseResponse("101"), HttpStatus.OK);
    }

    @ExceptionHandler({UserPasswordIncorrectException.class})
    private ResponseEntity<BaseResponse> handleUserPasswordIncorrectException(UserPasswordIncorrectException e) {
        return new ResponseEntity<>(new BaseResponse("102"), HttpStatus.OK);
    }

    @ExceptionHandler(MaribongException.class)
    private ResponseEntity<ErrorResponse> handleDuplicatedUserIdException(MaribongException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
