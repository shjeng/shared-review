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
    @Column(name = "comment_id")
    private Long id;

    // User테이블 - User_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    // Post테이블 - Post_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // 댓글 내용
    @Column(name = "comment_content", nullable = false)
    private String content;

    @Column(name = "comment_delete_status")
    private String deleteStatus = "N";


    public void setUserBoardContent(User user, Board board, String content) {
        this.user = user;
        this.board = board;
        this.content = content;
    }
    public void delete() {
        deleteStatus = "Y";
    }
}
