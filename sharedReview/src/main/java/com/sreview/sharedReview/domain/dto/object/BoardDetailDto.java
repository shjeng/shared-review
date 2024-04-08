package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDetailDto {
    private Long boardId;
    private String title;
    private Integer viewCount;
    private String content;
    private LocalDateTime updateDateTime;

    public void ofEntity(Board board) {
        boardId = board.getBoardId();
        this.title = board.getTitle();
        content = board.getContent();
        this.viewCount = board.getViewsCount();
        this.updateDateTime = board.getLastModifiedDate();
    }
}
