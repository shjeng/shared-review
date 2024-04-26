package com.sreview.sharedReview.domain.dto.object;


import com.sreview.sharedReview.domain.jpa.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private String content;
    private UserDto userDto; // 댓글 작성자?

    public static CommentDto of(Comment comment, UserDto userDto){
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .userDto(userDto)
                .build();
    }
}
