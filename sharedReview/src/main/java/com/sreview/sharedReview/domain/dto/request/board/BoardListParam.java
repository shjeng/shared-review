package com.sreview.sharedReview.domain.dto.request.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListParam {
    private String searchWord;
    private String searchType;
    private Long categoryId;
}
