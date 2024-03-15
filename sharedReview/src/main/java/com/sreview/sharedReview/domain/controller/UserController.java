package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/get-login-user") // 로그인을 한 경우 회원 정보 클라이언트로 넘겨주기.
    public ResponseEntity<? super GetLoginUserResponse> getLoginUser(@AuthenticationPrincipal String email){
        return userService.getLoginUser(email);
    }
}
