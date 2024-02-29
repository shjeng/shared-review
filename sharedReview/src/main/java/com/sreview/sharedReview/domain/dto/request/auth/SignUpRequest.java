package com.sreview.sharedReview.domain.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String nickname;
    private String password;

}
