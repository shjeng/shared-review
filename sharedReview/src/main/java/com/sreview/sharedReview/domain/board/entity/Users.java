package com.sreview.sharedReview.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity // 엔티티로 사용하겠다
@Getter // 객체의 필드 값을 반환하는 역할
@NoArgsConstructor // JPA는 엔티티를 생성할 때 기본 생성자를 사용. 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // 해당 클래스에 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class Users { // 사용자 테이블

    // 사용자 ID
    @Id @GeneratedValue
    @Column(name="Users_ID")
    private Long id;

    // 사용자 이메일
    @Column(name="Users_Email")
    private String email;

    // 사용자 비밀번호
    @Column(name="Users_Password")
    private String password;

    // 사용자 닉네임
    @Column(name="Users_Nickname")
    private String nickname;

    // 사용자 휴대전화 번호
    @Column(name="Users_Phone")
    private String phone;

    // 사용자 프로필 사진
    @Column(name="Users_ImageUrl")
    private String ImageUrl;

    // 사용자 관리자 권한
    @Column(name="Users_Admin")
    private String admin;

    // 게시물 테이블과의 일(users)대다(Posts) 관계 설정
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Posts> posts; // 자바에는 있지만 실제 DB에 가보면 User 테이블에는 POST 정보가 없어


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comments> comments;


}