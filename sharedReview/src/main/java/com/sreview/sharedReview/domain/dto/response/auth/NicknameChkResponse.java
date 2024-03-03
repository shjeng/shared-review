package com.sreview.sharedReview.domain.dto.response.auth;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class NicknameChkResponse extends ResponseDto {
    private String nickname;
    public NicknameChkResponse(String nickname) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nickname = nickname;
    }

    public static ResponseEntity<NicknameChkResponse> success(String nickname){
        return ResponseEntity.status(HttpStatus.OK).body(new NicknameChkResponse(nickname));
    }
    public static ResponseEntity<ResponseDto> nicknameDuplError(){ // 중복 닉네임
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME,ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
