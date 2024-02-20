// 태그 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tags { // 태그 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tags_ID")
    private Long id;

    @Column(name = "Tags_Name")
    private String name;

    // 일대다
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PostTags> postTags;
}
