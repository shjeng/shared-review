package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class accessTokenValidatorResponse extends ResponseDto {
    private String token; // front에 넘겨줄 token
    private int expirationTime; // 토큰 만료 시간

    public accessTokenValidatorResponse(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        expirationTime = 3600;
    }


    public static ResponseEntity<accessTokenValidatorResponse> success(String token){ // 새로운 토큰 반환
        return ResponseEntity.status(HttpStatus.OK).body(new accessTokenValidatorResponse(token));
    }

    public static ResponseEntity<ResponseDto> loginFail(){
        return null;
    }
}
