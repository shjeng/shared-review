package com.sreview.sharedReview.domain.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequest {
    private String email;
    private String password;
}
