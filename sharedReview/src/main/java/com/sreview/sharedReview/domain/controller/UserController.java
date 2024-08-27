package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.AdminBoardListResponse;
import com.sreview.sharedReview.domain.dto.response.board.BoardListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserResponse;
import com.sreview.sharedReview.domain.service.BoardService;
import com.sreview.sharedReview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BoardService boardService;
    @GetMapping("/get-login-user") // 로그인을 한 경우 회원 정보 클라이언트로 넘겨주기.
    public ResponseEntity<? super GetLoginUserResponse> getLoginUser(@AuthenticationPrincipal String email){
        System.out.println("받은 토큰 : " + email);
        return userService.getLoginUser(email);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<? super GetUserResponse> getUserInfo(@PathVariable("userEmail") String userEmail){
        ResponseDto result = userService.getUser(userEmail);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-user-list") // 유저 리스트
    public ResponseEntity<? super GetUserListResponse> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get-user-list/{searchValue}/{inputValue}") // 관리자 페이지 유저 검색
    public ResponseEntity<? super GetUserListResponse> getAdminUserSearch(@PathVariable("searchValue") String searchValue, @PathVariable("inputValue") String inputValue) {
        return ResponseEntity.ok(userService.getAdminUserSearch(searchValue, inputValue));

    }

    @GetMapping("/{userEmail}/board")
    public ResponseEntity<? super BoardListResponse> getUserBoard(@PathVariable("userEmail")String userEmail, @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        ResponseDto result = boardService.findBoardByUserEmail(userEmail, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-login-user/{userEmail}")
    public ResponseEntity<? super GetUserResponse> getUser(@PathVariable("userEmail") String userEmail){
        ResponseDto result = userService.getUser(userEmail);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/password-check")
    public ResponseEntity<?> passwordCheck(@AuthenticationPrincipal String email, @RequestBody Map<String, String> password){
        log.info("password: {}", password);
        ResponseDto result = userService.passwordCheck(email, password);
        return ResponseEntity.ok(result);
    }
    @PatchMapping("")
    public ResponseEntity<?> editUser(@AuthenticationPrincipal String email, @RequestBody SignUpRequest requestBody) {
        log.info("requestBody = {}", requestBody);
        requestBody.setEmail(email);
        ResponseDto result = userService.editUser(requestBody);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@AuthenticationPrincipal String email, @RequestBody Map<String, String> password){
        System.out.println("클라이언트에서 받아온 email 값 : "+email+", password 값 : "+ password);
        return ResponseEntity.ok("연결 완료");
    }
}
