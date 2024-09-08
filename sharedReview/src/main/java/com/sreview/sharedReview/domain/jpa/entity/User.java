package com.sreview.sharedReview.domain.jpa.entity;

import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user_entity")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    // 회원 ID
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    // 회원 이메일
    @Column(name = "User_Email")
    private String email;

    // 회원 비밀번호
    @Column(name = "User_Password")
    private String password;

    // 회원 닉네임
    @Setter
    @Column(name = "User_Nickname")
    private String nickname;

    // 회원 프로필 이미지
    @Column(name = "User_ImageUrl")
    private String imageUrl;

    // 회원 권한
//    @ColumnDefault("NORMAL") => default 값을 넣으려고 했는데 오류
    @Enumerated(EnumType.STRING)
    private ADMIN admin = ADMIN.NORMAL;

    // 회원 활동 여부
    @Column(name = "User_Active", nullable = false)
    private boolean active = true;

    // 초기값 세팅
    public void setUser(String email, String nickname, String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
    public void setProfileImage(String imageUrl){
        this.imageUrl = imageUrl;

    }

    public void setPassword(String hashedNewPassword){
        this.password = hashedNewPassword;
    }
}
