package com.sreview.sharedReview.domain.dto.request.board;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardWriteRequest {
    private String title;
    private String content;
    private String category;
    private List<String> tags;

    public static Board getBoard(BoardWriteRequest request){
        Board boardEntity = new Board();
        boardEntity.setTitleContent(request.title, request.content);
        return boardEntity;
    }
}
