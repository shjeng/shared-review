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
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Likes_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Users_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts post;
}
