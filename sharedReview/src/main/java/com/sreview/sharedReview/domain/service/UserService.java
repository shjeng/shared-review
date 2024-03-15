package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email);
}
