package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.auth.SignInRequest;
import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.auto.SignInResponse;
import com.sreview.sharedReview.domain.dto.response.auto.SignUpResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

//    ResponseEntity<? super NicknameDuplChkResponseDto> nicknameDuplChk(NicknameDuplChkRequestDto dto);
    ResponseEntity<? super SignInResponse> signIn(SignInRequest request);

    ResponseEntity<? super SignUpResponse> signUp(SignUpRequest request);

}
