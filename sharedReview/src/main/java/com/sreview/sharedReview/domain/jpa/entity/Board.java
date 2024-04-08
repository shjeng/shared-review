// 게시물 테이블
package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity // 엔티티로 사용하겠다
@Getter // 객체의 필드 값을 반환하는 역할
@NoArgsConstructor // JPA는 엔티티를 생성할 때 기본 생성자를 사용. 매개변수가 없는 기본 생성자를 생성
public class Board extends BaseEntity{ // 게시물 테이블
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
    private int viewsCount = 0;


    // 게시물 좋아요
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<LikeEntity> likes = new ArrayList<>();

    // img
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

//    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setTitleContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void setUserAndCategory(User user, Category category){
        this.user = user;
        this.category = category;
    }
}
