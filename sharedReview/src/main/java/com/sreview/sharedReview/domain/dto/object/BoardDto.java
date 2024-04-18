package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long boardId;
    private String title;
    private Integer commentCount;
    private Integer favoriteCount;
    private Integer viewCount;
    private String backImg;
    private LocalDateTime writeDateTime;
    private UserDto user;

    public BoardDto of(Board board){
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        commentCount = board.getCommentCount();
        favoriteCount = board.getFavoriteCount();
        viewCount = board.getViewsCount();
        backImg = null; //  나중에 이미지 업로드 기능 추가하면 ㄴ넣어야함.
        writeDateTime = board.getLastModifiedDate();
        user = UserDto.of(board.getUser());
        return this;
    }
}

