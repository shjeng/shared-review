package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetEmailAuthChk extends ResponseDto {
    private String email;

    public GetEmailAuthChk(String email) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = email;
    }
    // 인증 요청이 성공했을 경우
    public static ResponseEntity<GetEmailAuthChk> success(String email){
        return ResponseEntity.ok(new GetEmailAuthChk(email));
    }

    // 중복 이메일
    public static ResponseEntity<ResponseDto> dupleEmail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.DUPLICATE_EMAIL,ResponseMessage.DUPLICATE_EMAIL));
    }
}
