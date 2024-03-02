package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NicknameChkResponse extends ResponseDto {
    public NicknameChkResponse() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<NicknameChkResponse> success(){
        return ResponseEntity.status(HttpStatus.OK).body(new NicknameChkResponse());
    }
    public static ResponseEntity<ResponseDto> nicknameDuplChk(){ // 중복 닉네임
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME,ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
