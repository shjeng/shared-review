package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.dto.object.AdminUserDto;
import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserResponse;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import com.sreview.sharedReview.domain.jpa.service.UserEntityService;
import com.sreview.sharedReview.domain.service.UserService;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityService userEntityService;
    private final UserRepository userRepository;

    @Override // 로그인을 하면 가져올 데이터
    public ResponseEntity<? super GetLoginUserResponse> getLoginUser(String email) {
        UserDto userDto;
        try{
            User user = userEntityService.findByEmail(email);
            userDto = UserDto.of(user);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return GetLoginUserResponse.success(userDto);
    }

    @Override
    public ResponseDto getUser(String email) {
        User user = userEntityService.findByEmail(email);
        return GetUserResponse.success(UserDto.of(user));
    }

    @Override // user 정보 가져옴
    public ResponseDto getAllUsers() {
        try {
            List<User> userList = userRepository.findAll();
            List<AdminUserDto> userDtos = userList.stream().map(l -> new AdminUserDto().of(l)).toList(); // BoardDto로 변환

            return GetUserListResponse.success(userDtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }


    @Override
    public ResponseDto getAdminUserSearch(String searchValue, String inputValue) {
        System.out.println("Impl - 받아온 데이터 searchValue : " + searchValue);
        System.out.println("Impl - 받아온 데이터 inputValue : " +  inputValue);

        List<AdminUserDto> users;
        List<User> filteredUser;
        try {
//          게시글 제목으로 들어올때
            if("nickName".equals(searchValue)) {
                System.out.println("nickName 실행");
                filteredUser = userRepository.findAdminByNickname(inputValue);

//          유저 아이디로 들어올때
            } else if ("email".equals(searchValue)) {
                System.out.println("email 실행");
                filteredUser = userRepository.findAdminByEmail(inputValue);
            }
            else {
                System.out.println("!!!!!!!!!!!데이터 못찾음!!!!!!!!!!!!");
                return null;
            }
            System.out.println("쿼리문 실행 후 filteredCategorys : " + filteredUser);

            // DTO로 변환
            users = AdminUserDto.ofList(filteredUser);

//            return null;
            return GetUserListResponse.success(users);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
//            return AdminCategotyResponse.databaseError();
        }
    }


}
