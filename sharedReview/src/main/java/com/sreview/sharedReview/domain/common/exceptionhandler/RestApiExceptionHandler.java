package com.sreview.sharedReview.domain.common.exceptionhandler;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.common.customexception.CustomRuntimeException;
import com.sreview.sharedReview.domain.common.customexception.NonExistBoardException;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> databaseError(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR));
    }

    @ExceptionHandler(NonExistBoardException.class)
    public ResponseEntity<ResponseDto> noExistBoardException(NonExistBoardException e) {
        log.error("Non Exist Board Error");
        return ResponseEntity.badRequest().body(new ResponseDto(ResponseCode.NON_EXISTED_BOARD, ResponseMessage.NON_EXISTED_BOARD));
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ResponseDto> customExceptionHandler(CustomRuntimeException e) {
        log.error("이메일 중복 예외");
        return ResponseEntity.badRequest().body(new ResponseDto(ResponseCode.BAD_REQUEST, e.getMessage()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> runtimeException(RuntimeException e) {
        log.error("Run time exception: {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseDto> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException: {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ResponseDto> badRequestException(RuntimeException e) {
//        log.error("Bad Request(Runtime Exception)");
//        return ResponseEntity.badRequest().body(new ResponseDto(ResponseCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST));
//    }
}
