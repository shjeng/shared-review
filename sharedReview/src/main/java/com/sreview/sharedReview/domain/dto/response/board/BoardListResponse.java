package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.BoardDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@ToString
public class BoardListResponse extends ResponseDto {
    private List<BoardDto> boards;
    private String condition;
    private Page<BoardDto> boardPage;

    public BoardListResponse(List<BoardDto> boardDtoList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        boards = boardDtoList;
    }
    public BoardListResponse(Page<BoardDto> boardPage) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardPage = boardPage;
    }

    public BoardListResponse(List<BoardDto> boardDtoList, Page<BoardDto> boardPage) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        boards = boardDtoList;
        this.boardPage = boardPage;
    }

    public BoardListResponse(List<BoardDto> boardDtoList, String condition) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        boards = boardDtoList;
        this.condition = condition;
    }

    public static BoardListResponse success(List<BoardDto> boardDtoList, String condition) {
        return new BoardListResponse(boardDtoList, condition);
    }
    public static BoardListResponse success(List<BoardDto> boardDtoList, Page<BoardDto> boardPage) {
        return new BoardListResponse(boardDtoList, boardPage);
    }
    public static BoardListResponse success(List<BoardDto> boardDtoList) {
        return new BoardListResponse(boardDtoList);
    }
    public static BoardListResponse success(Page<BoardDto> boardDtoList) {
        return new BoardListResponse(boardDtoList);
    }
}
