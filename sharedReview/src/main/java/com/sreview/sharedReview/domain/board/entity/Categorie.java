// 카테고리 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    // 카테고리 ID
    @Id
    @GeneratedValue
    @Column(name = "Categorie_ID")
    private Long id;

    // 카테고리 명
    @Column(name = "Categorie_Name")
    private String name;

    // 카테고리 작성자
    @Column(name = "Categorie_Create_Name")
    private String createName;

    // 카테고리 작성 날짜
    @Column(name = "Categorie_Create_Date")
    private String createDate;

}
