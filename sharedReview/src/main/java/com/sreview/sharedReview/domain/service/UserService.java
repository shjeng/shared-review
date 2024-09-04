package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.AdminBoardListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email);

    ResponseDto getUser(String email);
    ResponseDto getAllUsers();

//    ResponseEntity<? super GetUserListResponse> getAdminUserSearch(String searchValue, String inputValue);

    ResponseDto getAdminUserSearch(String searchValue, String inputValue);

    ResponseDto editUser(SignUpRequest requestBody);

    ResponseDto passwordCheck(String email, Map<String, String> password);

    ResponseDto passwordUpdate(String email,  Map<String, String> password);

    ResponseDto updateNickname(String email,  Map<String, String> requestData);

}
