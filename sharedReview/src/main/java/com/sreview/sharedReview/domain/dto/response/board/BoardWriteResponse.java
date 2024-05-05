package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class BoardWriteResponse extends ResponseDto {
    private Long boardId;
    public BoardWriteResponse(Long boardId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardId = boardId;
    }

    public static ResponseEntity<BoardWriteResponse> success(Long boardId){
        return ResponseEntity.status(HttpStatus.OK).body(new BoardWriteResponse(boardId));
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.NON_EXISTED_USER, ResponseMessage.NON_EXISTED_USER));
    }
}
