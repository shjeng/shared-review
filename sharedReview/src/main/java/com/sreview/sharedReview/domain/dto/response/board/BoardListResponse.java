package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.BoardDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class BoardListResponse extends ResponseDto {
    private List<BoardDto> boards;
    private String condition;

    public BoardListResponse(List<BoardDto> boardDtoList, String condition) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        boards = boardDtoList;
        this.condition = condition;
    }
    public static BoardListResponse success(List<BoardDto> boardDtoList, String condition) {
        return new BoardListResponse(boardDtoList, condition);
    }
}
