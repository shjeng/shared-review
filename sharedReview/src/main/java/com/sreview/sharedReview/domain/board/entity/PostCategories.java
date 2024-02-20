// 게시물 - 카테고리 연결 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostCategories_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "Categories_ID")
    private Categories category;
}
