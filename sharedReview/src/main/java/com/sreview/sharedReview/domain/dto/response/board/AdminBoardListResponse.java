package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.AdminBoardDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AdminBoardListResponse extends ResponseDto {
    private List<AdminBoardDto> boards;


    public AdminBoardListResponse(List<AdminBoardDto> boards) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boards = boards;
    }

    public static AdminBoardListResponse success(List<AdminBoardDto> boards){
        System.out.println("return new AdminBoardListResponse(boards); : " + new AdminBoardListResponse(boards));

        return new AdminBoardListResponse(boards);

    }

    // 유효성 검사 실패
    public static ResponseEntity<ResponseDto> fail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL));
    }


}
