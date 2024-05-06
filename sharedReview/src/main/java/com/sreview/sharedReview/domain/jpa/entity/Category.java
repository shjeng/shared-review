// 카테고리 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Category extends BaseEntity {
    // 카테고리 ID
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    // 카테고리 명
    @Column(name = "category_Name")
    private String name;

    // 카테고리 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category")
    private List<Board> boards;
    @Builder
    public Category(String  name, User user) {
        this.name = name;
        this.user = user;
    }
}
