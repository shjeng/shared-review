package com.sreview.sharedReview.domain.dto.request.board;

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
}
