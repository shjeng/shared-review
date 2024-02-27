// 게시물 - 카테고리 연결 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCategorie { // 게시물-카테고리 연결 테이블
    // 게시물-카테고리 ID
    @Id
    @GeneratedValue
    @Column(name = "PostCategorie_ID")
    private Long id;
    
    // Post테이블 Post_ID 외래키
    
    // Categorie 테이블 Categorie_ID 외래키

}
