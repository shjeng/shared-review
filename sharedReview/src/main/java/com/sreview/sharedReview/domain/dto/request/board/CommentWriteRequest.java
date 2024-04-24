package com.sreview.sharedReview.domain.dto.request.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentWriteRequest {
    private String content;
    private Long boardId;
}
