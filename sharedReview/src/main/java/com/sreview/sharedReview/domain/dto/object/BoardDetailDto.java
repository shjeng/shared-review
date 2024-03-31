package com.sreview.sharedReview.domain.dto.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDetailDto {
    private String title;
    private Integer commentCount;
    private Integer favoriteCount;
    private Integer viewCount;
    private LocalDateTime updateDateTime;
}
