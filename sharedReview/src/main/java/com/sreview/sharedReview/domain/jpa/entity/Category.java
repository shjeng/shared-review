// 카테고리 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    // 카테고리 ID
    @Id
    @GeneratedValue
    @Column(name = "category_ID")
    private Long id;

    // 카테고리 명
    @Column(name = "category_Name")
    private String name;

    // 카테고리 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 카테고리 작성 날짜
    @Column(name = "category_Create_Date")
    private String createDate;

    @Builder
    public Category(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
