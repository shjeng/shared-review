package com.sreview.sharedReview.domain.board.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class PostDTO {
    private Long postId;
    private Long userId;
//    private UserDto user; // UserDto를 사용하도록 변경
    private String postTitle;
    private String postContent;
    private Long views;
    private LocalDateTime postCreatedAt;
    private LocalDateTime postEditDate;
}