package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.AdminCategoryDto;
import com.sreview.sharedReview.domain.dto.object.CategoryDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdminCategotyResponse extends ResponseDto {

    private List<AdminCategoryDto> categorys = new ArrayList<>();


    public AdminCategotyResponse(List<AdminCategoryDto> categorys) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.categorys = categorys;
    }

    public static ResponseEntity<AdminCategotyResponse> success(List<AdminCategoryDto> categorys){
        return ResponseEntity.ok(new AdminCategotyResponse(categorys));
    }

    // 유효성 검사 실패
    public static ResponseEntity<ResponseDto> fail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL));
    }
}
