package com.sreview.sharedReview.domain.dto.request.auth;

import com.sreview.sharedReview.domain.jpa.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String profileImage;
    private String email;
    //    private String authNumber; // 인증번호 추가 예정
    private String nickname;
    private String password;

    public static User of(SignUpRequest request, String password) {
        User user = new User();
        user.setUser(request.email, request.nickname, password);
        return user;
    }
}
