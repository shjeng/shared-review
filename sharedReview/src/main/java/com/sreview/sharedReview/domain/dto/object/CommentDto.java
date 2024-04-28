package com.sreview.sharedReview.domain.dto.object;


import com.sreview.sharedReview.domain.jpa.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private String content;
    private UserDto user; // 댓글 작성자?
    private LocalDateTime createDateTime;

    public static CommentDto of(Comment comment, UserDto userDto){
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .user(userDto)
                .createDateTime(comment.getCreateDate())
                .build();
    }

}
