package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class tokenStatusResponse extends ResponseDto {
    private String token; // front에 넘겨줄 token
    private int expirationTime; // 토큰 만료 시간

    public tokenStatusResponse(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        expirationTime = 3600;
    }

    public tokenStatusResponse() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }


    public static ResponseEntity<tokenStatusResponse> newTokenSuccess(String token){ // 새로운 토큰 반환
        return ResponseEntity.status(HttpStatus.OK).body(new tokenStatusResponse(token));
    }

    public static ResponseEntity<tokenStatusResponse> success(){ // 유효한 응답
        return ResponseEntity.status(HttpStatus.OK).body(new tokenStatusResponse());
    }

    public static ResponseEntity<ResponseDto> tokenExpired(){ // 토큰 만료 에러
        ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_EXPIRED,ResponseMessage.TOKEN_EXPIRED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
