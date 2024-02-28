// 좋아요 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    // 좋아요 ID
    @Id
    @GeneratedValue
    @Column(name = "Like_ID")
    private Long id;

    // User테이블 User_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Users_ID")
    private User user;

    // post테이블 Post_ID 외래키
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Board_Id")
    private Board board;

}
