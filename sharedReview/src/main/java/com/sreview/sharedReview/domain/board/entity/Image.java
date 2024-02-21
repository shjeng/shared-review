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
public class Image { // 이미지 테이블
    // 이미지 ID
    @Id
    @GeneratedValue
    @Column(name = "Image_ID")
    private Long id;

    // Post테이블 Post_ID 외래키

    // 이미지 URL
    @Column(name = "Image_Url")
    private String url;
}
