package com.sreview.sharedReview.domain.dto.request.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardListParam {
    private String searchWord;
    private String searchType;
    private Long categoryId;
}
