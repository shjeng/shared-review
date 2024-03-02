package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponse extends ResponseDto {

    private String token; // front에 넘겨줄 token
    private int expirationTime; // 토큰 만료 시간

    public SignInResponse(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        expirationTime = 3600;
    }


    public static ResponseEntity<SignInResponse> success(String token){ // 로그인 성공 시
        return ResponseEntity.status(HttpStatus.OK).body(new SignInResponse(token));
    }

    public static ResponseEntity<ResponseDto> loginFail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.SIGN_IN_FAIL,ResponseMessage.SIGN_IN_FAIL));
    }
}
