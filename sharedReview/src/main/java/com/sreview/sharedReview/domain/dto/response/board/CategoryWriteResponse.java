package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CategoryWriteResponse extends ResponseDto {
    public CategoryWriteResponse() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public static ResponseEntity<CategoryWriteResponse> success(){ // 성공
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryWriteResponse());
    }
    public static ResponseEntity<ResponseDto> duplCategory(){ // 중복 카테고리가 있을 경우 에러 코드 발생
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.DUPLICATE_CATEGORY,ResponseMessage.DUPLICATE_CATEGORY));
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.NON_EXISTED_USER,ResponseMessage.NON_EXISTED_USER));
    }
}
