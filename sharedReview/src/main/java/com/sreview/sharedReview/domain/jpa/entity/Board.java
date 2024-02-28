// 게시물 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity // 엔티티로 사용하겠다
@Getter // 객체의 필드 값을 반환하는 역할
@Setter
@NoArgsConstructor // JPA는 엔티티를 생성할 때 기본 생성자를 사용. 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // 해당 클래스에 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class Board { // 게시물 테이블
    // 게시물 ID
    @Id
    @GeneratedValue
    @Column(name = "Board_ID")
    private Long boardId;

    // 유저 ID
    // User테이블 User_ID 외래키
    //    // 다(Posts)대일(Users) 관계
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Users_ID")
    private User user;

    // 게시물 제목
    @Column(name = "Board_Title")
    private String title;

    // 게시물 내용
    @Column(name = "Board_Content")
    private String content;

    // 게시물 조회수
    @Column(name = "Board_ViewsCount")
    private int viewsCount;

    // 게시물 작성 날짜
    @Column(name = "Board_CreatedAt")
    private LocalDateTime createdAt;

    // 게시물 수정 날짜
    @Column(name = "Board_EditDate")
    private LocalDateTime editDate;

    // 게시물 좋아요
    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    // img
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "comments",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Categorie_ID")
    private Category categorie;


}
