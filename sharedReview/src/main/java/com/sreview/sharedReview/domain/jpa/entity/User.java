package com.sreview.sharedReview.domain.jpa.entity;

import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_entity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
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

}
