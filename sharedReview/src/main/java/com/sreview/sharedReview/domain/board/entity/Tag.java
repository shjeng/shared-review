// 태그 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tag { // 태그 테이블
    // 태그 ID
    @Id
    @GeneratedValue
    @Column(name = "Tag_ID")
    private Long id;

    // 태그 명
    @Column(name = "Tag_Name")
    private String name;

//    // 일대다
//    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
//    private List<PostTags> postTags;
}
