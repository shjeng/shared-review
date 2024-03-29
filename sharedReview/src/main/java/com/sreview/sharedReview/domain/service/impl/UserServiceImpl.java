package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.UserEntityService;
import com.sreview.sharedReview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityService userEntityService;
    @Override // 로그인을 하면 가져올 데이터
    public ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email) {
        UserDto userDto;
        try{
            Optional<User> optionalUser = userEntityService.findByEmail(email);
            if(optionalUser.isEmpty()) GetLoginUserResponse.noExistedUser();
            User user = optionalUser.get();
            userDto = UserDto.of(user);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return GetLoginUserResponse.success(userDto);
    }
}
