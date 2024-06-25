package com.sreview.sharedReview.domain.service.impl;


import com.sreview.sharedReview.domain.dto.request.auth.*;
import com.sreview.sharedReview.domain.dto.response.auth.*;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.UserEntityService;
import com.sreview.sharedReview.domain.provider.JwtProvider;
import com.sreview.sharedReview.domain.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
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
        String refreshToken = "";
        try {
            User user = userEntityService.findByEmail(request.getEmail());
            String encodedPassword = user.getPassword();
            boolean isMatched = passwordEncoder.matches(request.getPassword(), encodedPassword);
            if (!isMatched) return SignInResponse.loginFail();

            token = jwtProvider.create(user.getEmail());
            refreshToken = jwtProvider.createRefreshToken(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            SignInResponse.databaseError();
        }
        return SignInResponse.success(token, refreshToken);
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
            Optional<User> findExistingUser = userEntityService.existCheckEmail(request.getEmail());
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


    @Override
    public ResponseEntity<? super accessTokenValidatorResponse> validateToken(String token) {
        try {
            jwtProvider.validate(token);
            return ResponseEntity.ok().body("Valid token"); // 얘는 유효해! 써도 괜찮아!
        } catch (ExpiredJwtException ex) { // 얘는 jwt토큰 유효 만료라서 에러야.
            String email = ex.getClaims().getSubject();
            System.out.println("추출한 ? email값 : "+email);
            String newAccessToken = jwtProvider.create(email);
            System.out.println("추출한 ? newAccessToken값 : "+newAccessToken);

            return accessTokenValidatorResponse.success(newAccessToken);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
