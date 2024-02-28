package com.sreview.sharedReview.domain.jpa.entity;

import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
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
    @Column(name = "user_id")
    private Long id;

    // 회원 이메일
    @Column(name = "User_Email")
    private String email;

    // 회원 비밀번호
    @Column(name = "User_Password")
    private String password;

    // 회원 닉네임
    @Column(name = "User_Nickname")
    private String nickname;

    // 회원 프로필 이미지
    @Column(name = "User_ImageUrl")
    private String imageUrl;

    // 회원 권한
    @Enumerated(EnumType.STRING)
    private ADMIN admin;

    // 회원 가입 날짜
    @Column(name = "User_CreateDate")
    private Long createDate;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

}
