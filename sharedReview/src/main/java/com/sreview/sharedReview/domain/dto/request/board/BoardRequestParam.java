package com.sreview.sharedReview.domain.dto.request.board;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class BoardRequestParam {
    private String searchWord;
    private String searchType;
    private Long categoryId;
}
