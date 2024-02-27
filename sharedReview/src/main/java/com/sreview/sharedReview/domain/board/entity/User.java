package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 회원 ID
    @Id @GeneratedValue
    @Column(name = "User_ID")
    private Long id;

    // 회원 이메일
    @Column(name = "User_Email")
    private Long email;

    // 회원 비밀번호
    @Column(name = "User_Password")
    private Long password;

    // 회원 닉네임
    @Column(name = "User_Nickname")
    private Long nickname;

    // 회원 프로필 이미지
    @Column(name = "User_ImageUrl")
    private Long imageUrl;

    // 회원 권한
    @Column(name = "User_Admin")
    private Long admin;

    // 회원 가입 날짜
    @Column(name = "User_CreateDate")
    private Long createDate;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

}
