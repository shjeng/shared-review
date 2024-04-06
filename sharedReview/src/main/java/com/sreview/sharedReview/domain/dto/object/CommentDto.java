package com.sreview.sharedReview.domain.dto.object;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long commentId;
    private String comment;
    private UserDto userDto; // 댓글 작성자?
}
