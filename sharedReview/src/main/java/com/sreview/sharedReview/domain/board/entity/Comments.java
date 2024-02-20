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
public class Comments { // 댓글 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Comments_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Users_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts post;

    @Column(name = "Comments_Content", nullable = false)
    private String content;

    @Column(name = "Comments_CreatedAt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
