package com.sreview.sharedReview.domain.dto.request.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentWriteRequest {
    private String content;
    private Long boardId;
    private Integer currentPage;
}
