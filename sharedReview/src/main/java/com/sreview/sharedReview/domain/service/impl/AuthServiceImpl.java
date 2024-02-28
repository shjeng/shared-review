package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.dto.request.auth.SignInRequest;
import com.sreview.sharedReview.domain.dto.response.auto.SignInResponse;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.UserService;
import com.sreview.sharedReview.domain.provider.JwtProvider;
import com.sreview.sharedReview.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<? super SignInResponse> signIn(SignInRequest request) {
        String token = "";
        try{
            Optional<User> getUser = userService.findByEmail(request.getEmail());
            if(getUser.isEmpty()) return SignInResponse.loginFail(); // 없는 유저인 경우.

            User user = getUser.get();
            String encodedPassword = user.getPassword();
            boolean isMatched = passwordEncoder.matches(request.getPassword(),encodedPassword);
            if(!isMatched) return SignInResponse.loginFail();

            jwtProvider.create(user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            SignInResponse.databaseError();
        }
        return SignInResponse.success(token);
    }
}
