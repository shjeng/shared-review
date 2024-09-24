package com.sreview.sharedReview.domain.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonTokenUpdatePassword {
    private String userEmail;
    private String modifyPassword;
}
