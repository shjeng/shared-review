package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.auth.*;
import com.sreview.sharedReview.domain.dto.response.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthService {

    ResponseEntity<? super SignInResponse> signIn(SignInRequest request);

    ResponseEntity<? super GetEmailAuthChk> getEmailAuth(EmailAuthRequest request);

    ResponseEntity<? super AuthNumberChk> getEmailAuthNumber(EmailAuthNumberRequest request);
    ResponseEntity<? super SignUpResponse> signUp(SignUpRequest request);

    ResponseEntity<? super NicknameChkResponse> nicknameChk(String nickname);

    ResponseEntity<String> validateToken(String token);
}
