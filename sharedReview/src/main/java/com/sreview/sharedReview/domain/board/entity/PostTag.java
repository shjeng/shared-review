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
public class PostTag { // 게시물-태그 연결 테이블
    // 게시물-태그 ID
    @Id
    @GeneratedValue
    @Column(name = "PostTag_ID")
    private Long id;

    // Tag테이블 Tag_ID 외래키

    // Post테이블 Post_ID 외래키

    // 다대일
    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Post post;

    // 다대일
    @ManyToOne
    @JoinColumn(name = "Tags_ID")
    private Tag tag;
}
