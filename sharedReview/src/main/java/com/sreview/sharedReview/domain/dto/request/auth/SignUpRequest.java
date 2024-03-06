package com.sreview.sharedReview.domain.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
//    private String authNumber; // 인증번호 추가 예정
    private String nickname;
    private String password;

}
