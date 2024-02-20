// 게시물 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity // 엔티티로 사용하겠다
@Getter // 객체의 필드 값을 반환하는 역할
@NoArgsConstructor // JPA는 엔티티를 생성할 때 기본 생성자를 사용. 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // 해당 클래스에 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class Posts { // 게시물 테이블
    // 사용자 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Posts_ID")
    private Long id;

    // 다(Posts)대일(Users) 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Users_ID")
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Images> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostCategories> postCategories;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostTags> postTags;

    @Column(name = "Posts_Title")
    private String title;

    @Column(name = "Posts_Content")
    private String content;

    @Column(name = "Posts_CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "Posts_LikesCount")
    private int likesCount;

    @Column(name = "Posts_CommentsCount")
    private int commentsCount;

    @Column(name = "Posts_ViewsCount")
    private int viewsCount;

}
