package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.BoardDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class BoardListResponse extends ResponseDto {
    private BoardDto boardDto;

    public BoardListResponse(BoardDto boardDto) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardDto = boardDto;
    }
    public static ResponseEntity<BoardListResponse> success(BoardDto boardDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new BoardListResponse(boardDto));
    }
}
