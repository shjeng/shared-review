// 게시물 테이블
package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // 엔티티로 사용하겠다
@Getter // 객체의 필드 값을 반환하는 역할
@NoArgsConstructor // JPA는 엔티티를 생성할 때 기본 생성자를 사용. 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // 해당 클래스에 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class Post { // 게시물 테이블
    // 게시물 ID
    @Id
    @GeneratedValue
    @Column(name = "Post_ID")
    private Long id;

    // User테이블 User_ID 외래키
    //    // 다(Posts)대일(Users) 관계
    @ManyToOne
    @JoinColumn(name = "Users_ID")
    private User user;

    // 게시물 제목
    @Column(name = "Post_Title")
    private String title;

    // 게시물 내용
    @Column(name = "Post_Content")
    private String content;

    // 게시물 작성 날짜
    @Column(name = "Post_CreatedAt")
    private LocalDateTime createdAt;

    // 게시물 조회수
    @Column(name = "Post_ViewsCount")
    private int viewsCount;

    // 게시물 수정 날짜
    @Column(name = "Post_EditDate")
    private LocalDateTime editDate;

//    // 게시물 좋아요
//    @Column(name = "Post_LikesCount")
//    private int likesCount;

}
