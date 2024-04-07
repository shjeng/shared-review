// 댓글 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{ // 댓글 테이블
    // 댓글 ID
    @Id
    @GeneratedValue
    @Column(name = "Comment_ID")
    private Long id;

    // User테이블 - User_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Users_ID")
    private User user;

    // Post테이블 - Post_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Board_ID")
    private Board board;

    // 댓글 내용
    @Column(name = "Comment_Content", nullable = false)
    private String content;



    public void setUserBoardContent(User user, Board board, String content) {
        this.user = user;
        this.board = board;
        this.content = content;
    }
}
