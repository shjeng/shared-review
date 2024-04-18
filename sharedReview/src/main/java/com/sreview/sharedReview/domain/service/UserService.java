package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email);

    ResponseDto getAllUsers();
}
