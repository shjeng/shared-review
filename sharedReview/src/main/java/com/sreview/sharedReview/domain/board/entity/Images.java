// 이미지 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Images_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts post;

    @Column(name = "Images_Url")
    private String url;
}
