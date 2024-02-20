// 게시물 - 태그 연결 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostTags_ID")
    private Long id;

    // 다대일
    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts post;

    // 다대일
    @ManyToOne
    @JoinColumn(name = "Tags_ID")
    private Tags tag;
}
