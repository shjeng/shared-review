// 댓글 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment { // 댓글 테이블
    // 댓글 ID
    @Id
    @GeneratedValue
    @Column(name = "Comment_ID")
    private Long id;

    // User테이블 - User_ID 외래키

    // Post테이블 - Post_ID 외래키

    // 댓글 내용
    @Column(name = "Comment_Content", nullable = false)
    private String content;

    // 댓글 작성 날짜
    @Column(name = "Comment_CreatedAt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
