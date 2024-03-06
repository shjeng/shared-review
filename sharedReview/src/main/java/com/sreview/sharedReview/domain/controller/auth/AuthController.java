package com.sreview.sharedReview.domain.controller.auth;


import com.sreview.sharedReview.domain.dto.request.auth.NicknameChkRequest;
import com.sreview.sharedReview.domain.dto.request.auth.SignInRequest;
import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.auth.GetEmailAuthChk;
import com.sreview.sharedReview.domain.dto.response.auth.NicknameChkResponse;
import com.sreview.sharedReview.domain.dto.response.auth.SignInResponse;
import com.sreview.sharedReview.domain.dto.response.auth.SignUpResponse;
import com.sreview.sharedReview.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
//    @RestController:
//    이 어노테이션은 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다. 이 컨트롤러는 HTTP 요청을 처리하고 HTTP 응답을 생성하는 역할을 합니다.
//    @RequiredArgsConstructor:
//    이 어노테이션은 클래스의 final 필드나 @NonNull 필드에 대한 생성자를 자동으로 생성합니다.
//    @RequestMapping("/api/auth"):
//    이 어노테이션은 해당 컨트롤러의 모든 요청 매핑에 대해 기본 경로를 지정합니다. 따라서 이 컨트롤러의 모든 엔드포인트는 "/api/auth"로 시작합니다.


//    @GetMapping("/nickname-check") // HTTP GET 요청에 대한 핸들러, "/nickname-check" 경로에 매핑
//    public ResponseEntity<? super NicknameDuplChkResponseDto> nicknameDuplChk(@RequestBody NicknameDuplChkRequestDto dto){
//        // nicknameDuplChk 메소드 정의, RequestBody 어노테이션으로 요청 본문에서 NicknameDuplChkRequestDto 수신
//        ResponseEntity<? super NicknameDuplChkResponseDto> response = authService.nicknameDuplChk(dto);
//        // authService의 nicknameDuplChk 메소드 호출하여 닉네임 중복 확인 요청 처리
//        return response; // 처리된 결과 반환
//    }

    @GetMapping("/nickname-chk")
    public ResponseEntity<? super NicknameChkResponse> nicknameChk(@RequestBody NicknameChkRequest request){
        return authService.nicknameChk(request);
    }
    @GetMapping("/email-auth")
    public ResponseEntity<? super GetEmailAuthChk> getEmailAuthChk(){
        return null;
    }
    @PostMapping("/sign-in") // 로그인, 먼저 request dto를 controller에서 받아준다.
    public ResponseEntity<? super SignInResponse> signIn(@RequestBody SignInRequest request){
        return authService.signIn(request);
    }

    @PostMapping("/sign-up") // 회원가입
    public ResponseEntity<? super SignUpResponse> signUp(@RequestBody SignUpRequest request){
        return authService.signUp(request);
    }


}
