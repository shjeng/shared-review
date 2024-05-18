package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.AdminBoardListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email);

    ResponseDto getUser(String email);
    ResponseDto getAllUsers();

//    ResponseEntity<? super GetUserListResponse> getAdminUserSearch(String searchValue, String inputValue);

    ResponseDto getAdminUserSearch(String searchValue, String inputValue);

}
