package com.sreview.sharedReview.domain.service.impl;


import com.sreview.sharedReview.domain.dto.request.auth.*;
import com.sreview.sharedReview.domain.dto.response.auth.*;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.UserEntityService;
import com.sreview.sharedReview.domain.provider.JwtProvider;
import com.sreview.sharedReview.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserEntityService userEntityService;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String verificationCode; // 클래스의 필드로 저장

    @Override
    public ResponseEntity<? super SignInResponse> signIn(SignInRequest request) {
        String token = "";
        try {
            Optional<User> getUser = userEntityService.findByEmail(request.getEmail());
            if (getUser.isEmpty()) return SignInResponse.loginFail(); // 없는 유저인 경우.

            User user = getUser.get();
            String encodedPassword = user.getPassword();
            boolean isMatched = passwordEncoder.matches(request.getPassword(), encodedPassword);
            if (!isMatched) return SignInResponse.loginFail();

            jwtProvider.create(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            SignInResponse.databaseError();
        }
        return SignInResponse.success(token);
    }

    @Override // 이메일 인증 요청 로직 구현할 예정
    public ResponseEntity<? super GetEmailAuthChk> getEmailAuth(EmailAuthRequest request) {
        try {
            // 인증 코드 생성 로직
            verificationCode = UserEntityService.generateVerificationCode();

            // 이메일 전송
            userEntityService.sendVerificationEmail(request.getU_mail(), verificationCode);

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            // 실패 응답 또는 예외 처리 로직 추가
        }
//        return null;
        return GetEmailAuthChk.success(request.getU_mail());
    }


    @Override // 이메일 인증번호 일치 확인
    public ResponseEntity<? super AuthNumberChk> getEmailAuthNumber(EmailAuthNumberRequest request) {
        try {
            System.out.println("request.getAuthNumber() : " + request.getAuthNumber());
            System.out.println("verificationCode : " + verificationCode);

            if (request.getAuthNumber().equals(verificationCode)) {
                return AuthNumberChk.success(request.getAuthNumber());
            } else {
                return AuthNumberChk.failure();
            }
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            // 실패 응답 또는 예외 처리 로직 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<? super SignUpResponse> signUp(SignUpRequest request) {
        try {
            // 중복 회원(이메일)
            Optional<User> findExistingUser = userEntityService.findByEmail(request.getEmail());
            if (findExistingUser.isPresent()) return SignUpResponse.existingUser();

            // 중복 회원(닉네임)
            findExistingUser = userEntityService.findByNickname(request.getNickname());
            if (findExistingUser.isPresent()) return SignUpResponse.existingUser();

            String password = passwordEncoder.encode(request.getPassword());
            User user = SignUpRequest.of(request, password);
            userEntityService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return SignInResponse.databaseError();
        }
        return SignUpResponse.sucess();
    }

    @Override
    public ResponseEntity<? super NicknameChkResponse> nicknameChk(String nickname) {
        try {
            Optional<User> userOptional = userEntityService.findByNickname(nickname);
            if (userOptional.isPresent()) return NicknameChkResponse.nicknameDuplError();
        } catch (Exception e) {
            e.printStackTrace();
            return NicknameChkResponse.databaseError();
        }
        return NicknameChkResponse.success(nickname);
    }
}
