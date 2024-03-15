package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthNumberChk extends ResponseDto {
    private String authNumber;

    public AuthNumberChk(String authNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.authNumber = authNumber;
    }

    // 인증번호 확인 요청이 성공했을 경우
    public static ResponseEntity<AuthNumberChk> success(String verificationCode) {
        return ResponseEntity.ok(new AuthNumberChk(verificationCode));
    }

    // 인증번호 확인 요청이 실패했을 경우
    public static ResponseEntity<ResponseDto> failure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(ResponseCode.AUTH_NUMBER_FAILED, ResponseMessage.AUTH_NUMBER_FAILED));
    }
}
