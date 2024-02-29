package com.sreview.sharedReview.domain.dto.response.auto;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
public class SignUpResponse extends ResponseDto {

    public SignUpResponse() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public static ResponseEntity<SignUpResponse> sucess(){
        return ResponseEntity.status(HttpStatus.OK).body(new SignUpResponse());
    }
}
