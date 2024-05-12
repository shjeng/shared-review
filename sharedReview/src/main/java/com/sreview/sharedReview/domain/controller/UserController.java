package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.BoardListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import com.sreview.sharedReview.domain.service.BoardService;
import com.sreview.sharedReview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BoardService boardService;
    @GetMapping("/get-login-user") // 로그인을 한 경우 회원 정보 클라이언트로 넘겨주기.
    public ResponseEntity<? super GetLoginUserResponse> getLoginUser(@AuthenticationPrincipal String email){
        return userService.getLoginUser(email);
    }

    @GetMapping("/get-user-list") // 유저 리스트
    public ResponseEntity<? super GetUserListResponse> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userEmail}/board")
    public ResponseEntity<? super BoardListResponse> getUserBoard(@PathVariable("userEmail")String userEmail, @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        ResponseDto result = boardService.findBoardByUserEmail(userEmail, pageable);
        return ResponseEntity.ok(result);
    }
}
