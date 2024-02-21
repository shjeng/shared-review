// 좋아요 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    // post테이블 Post_ID 외래키
}
