package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.auth.EmailAuthRequest;
import com.sreview.sharedReview.domain.dto.request.auth.NicknameChkRequest;
import com.sreview.sharedReview.domain.dto.request.auth.SignInRequest;
import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.auth.GetEmailAuthChk;
import com.sreview.sharedReview.domain.dto.response.auth.NicknameChkResponse;
import com.sreview.sharedReview.domain.dto.response.auth.SignInResponse;
import com.sreview.sharedReview.domain.dto.response.auth.SignUpResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignInResponse> signIn(SignInRequest request);

    ResponseEntity<? super GetEmailAuthChk> getEmailAuth(EmailAuthRequest request);
    ResponseEntity<? super SignUpResponse> signUp(SignUpRequest request);

    ResponseEntity<? super NicknameChkResponse> nicknameChk(String nickname);
}
