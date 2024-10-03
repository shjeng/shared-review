package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.common.customexception.BadRequestException;
import com.sreview.sharedReview.domain.common.customexception.NonExistUserException;
import com.sreview.sharedReview.domain.dto.object.AdminUserDto;
import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.request.auth.NonTokenUpdatePassword;
import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.auth.tokenStatusResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetLoginUserResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserListResponse;
import com.sreview.sharedReview.domain.dto.response.user.GetUserResponse;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import com.sreview.sharedReview.domain.jpa.service.UserEntityService;
import com.sreview.sharedReview.domain.provider.JwtProvider;
import com.sreview.sharedReview.domain.service.UserService;
import com.sun.jdi.InternalException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityService userEntityService;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        List<AdminUserDto> users;
        List<User> filteredUser = null;

        try {
            if("id".equals(searchValue)) { // 아이디로 들어올때
                filteredUser = userRepository.findAdminById(inputValue);
            }else if("nickName".equals(searchValue)) {  // 닉네임으로 들어올때
                filteredUser = userRepository.findAdminByNickname(inputValue);
            } else if ("email".equals(searchValue)) { // 이메일로 들어올때
                filteredUser = userRepository.findAdminByEmail(inputValue);
            } else if ("active".equals(searchValue)) {
                Boolean activeInputValue = null;

                if("유효".equals(inputValue)) {
                    activeInputValue = true;
                } else if ("탈퇴".equals(inputValue)) {
                    activeInputValue = false;
                }
                filteredUser = userRepository.findAdminByActive(activeInputValue);
            }
            // DTO로 변환
            users = AdminUserDto.ofList(filteredUser);

            return GetUserListResponse.success(users);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @Override
    public ResponseDto editUser(SignUpRequest requestBody) {
        try {
            User user = userEntityService.findByEmail(requestBody.getEmail());
            if (!requestBody.getNickname().isEmpty()) {
                Optional<User> nicknameOptional = userEntityService.findByNickname(requestBody.getNickname());
                if (nicknameOptional.isPresent()) {
                    User dupleCheck = nicknameOptional.get();
                    if (dupleCheck.getEmail().equals(requestBody.getEmail())) {
                        throw new BadRequestException("닉네임 중복입니다.");
                    }
                }
                user.setNickname(requestBody.getNickname());
            }
            if (!requestBody.getPassword().equals(requestBody.getPasswordCheck())) {
                throw new BadRequestException("비밀번호가 일치하지 않습니다.");
            }
            if (!requestBody.getProfileImage().isEmpty()) {
                String newFileURl = fileService.tempImageToSave(requestBody.getProfileImage());
                user.setProfileImage(newFileURl);
            }
            userRepository.save(user);
            return new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseDto passwordCheck(String email, Map<String, String> password) {
        User user = userEntityService.findByEmail(email);
        String encodedPassword = user.getPassword();
        boolean isMatched = passwordEncoder.matches(password.get("password"), encodedPassword);

//        Optional<User> userOptional = userEntityService.passwordCheck(email, password.get("password"));
//        if (!isMatched) {
//            return new ResponseDto(ResponseCode.NON_EXISTED_USER, ResponseMessage.NON_EXISTED_USER);
//        }
//        return new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        if (!isMatched) {
            System.out.println("비밀번호가 일치하지 않을때");
            ResponseDto response = new ResponseDto(ResponseCode.NON_EXISTED_USER, ResponseMessage.NON_EXISTED_USER);
            return response;
        }

        System.out.println("비밀번호가 일치할때");
        ResponseDto response = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return response;
    }

    @Override
    public ResponseDto passwordUpdate(String email, Map<String, String> passwordMap) {
        // 1. 사용자 조회
        User user = userEntityService.findByEmail(email);

        // 2. 현재 비밀번호 검증
        String currentPassword = passwordMap.get("password");
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return new ResponseDto("NU", "현재 비밀번호가 일치하지 않습니다.");
        }

        // 3. 새 비밀번호 해싱
        String newPassword = passwordMap.get("modifyPassword");
        String hashedNewPassword = passwordEncoder.encode(newPassword);

        // 4. 비밀번호 업데이트
        user.setPassword(hashedNewPassword);
        System.out.println("쿼리 보내는 user 값 : " + user);
        userRepository.save(user);

        // 5. 결과 반환
        return new ResponseDto("SU", "비밀번호가 변경되었습니다.");
    }

    @Override
    public ResponseDto updateNickname(String email, Map<String, String> requestData) {
        // 1. 사용자 조회
        User user = userEntityService.findByEmail(email);

        // 2. 현재 비밀번호 검증
        String currentPassword = requestData.get("password");
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return new ResponseDto("NU", "현재 비밀번호가 일치하지 않습니다.");
        }

        // 3. 새 닉네임
        String newNickname = requestData.get("modifyNickname");
        Optional<User> nicknameOptional = userEntityService.findByNickname(newNickname);
        if (nicknameOptional.isPresent()) {
            User dupleCheck = nicknameOptional.get();
            if (dupleCheck.getEmail().equals(email)) {
                throw new BadRequestException("닉네임 중복입니다.");
            }
        }
        user.setNickname(newNickname);

        System.out.println("쿼리 보내는 user 값 : " + user);
        userRepository.save(user);

        // 5. 결과 반환
        return new ResponseDto("SU", "닉네임이 변경되었습니다.");
    }

    @Override
    public ResponseDto deleteUser(String token, Map<String, String> emailRequest) {
        try {
            // 1. 토큰 유효성 검사
            String pureToken = token.replace("Bearer ", "").trim();
            jwtProvider.validate(pureToken);

            // 2. 유효한 토큰에서 이메일 추출
            String tokenEmail = jwtProvider.getEmailFromToken(pureToken);

            // 3. 클라이언트에서 전송된 이메일
            String providedEmail = emailRequest.get("deleteUserEmail");

            if (tokenEmail.equals(providedEmail)) {
                // 4. DB에서 이메일 확인
                boolean emailExists = userEntityService.findByEmail(providedEmail).getActive();
                if (emailExists) { // 5. 이메일이 존재하고 해당 user의 active가 1인(유효할) 경우
                    User user = userEntityService.findByEmail(tokenEmail);
                    // active값을 0으로 바꿈
                    user.setActive(false);
                    // withdrawalTime값을 현재 시간으로 변경
                    if(user.getWithdrawalTime() == null) {
                        user.setWithdrawalTime(LocalDateTime.now());
                    } else if(user.getWithdrawalTime() != null) {
                        return new ResponseDto("DA", "탈퇴 날짜가 있음");
                    }
                    userEntityService.save(user);
                    return new ResponseDto("SU", "회원탈퇴 완료");
                } else {// 이메일이 DB에 존재하지 않는 경우
                    return new ResponseDto("VF", "db에 이메일 존재x");
                }
            } else {// 이메일이 일치하지 않는 경우
                return new ResponseDto("VF", "로그인중인 계정과 입력한 이메일이 다릅니다.");
            }
        } catch (NoSuchElementException ex) {
            return new ResponseDto("NE", "존재하지 않은 사용자 입니다."); // 사용자 없음 예외 처리

        } catch (ExpiredJwtException ex) {
            System.out.println("2. 엑세스 토큰이 유효하지 않음.");
            return new ResponseDto("TE", "다시 로그인 해주세요.");

        } catch (Exception ex) {
            ex.printStackTrace(); // 예외 메시지 출력
            return new ResponseDto("IT", "유효하지 않은 토큰입니다.");
        }
    }


    @Override
    public ResponseDto nonTokenUpdatePassword(NonTokenUpdatePassword request) {
        try {
            System.out.println("request.getUserEmail() : " + request.getUserEmail());
            User user = userEntityService.findByEmail(request.getUserEmail());
            boolean userActive = user.getActive();
            System.out.println("userActive 값 : "+userActive);
            if(!userActive) {
                return new ResponseDto(ResponseCode.NON_EXISTED_USER,ResponseMessage.NON_EXISTED_USER);
            }
            String modifyPassword = request.getModifyPassword();
            String hashedNewPassword = passwordEncoder.encode(modifyPassword);

            user.setPassword(hashedNewPassword);
            userRepository.save(user);
            return new ResponseDto("SU", "비밀번호 변경 완료");

        }catch (NonExistUserException ex) {
            System.out.println("NonExistUserException 발생: " + ex.getMessage());
            return new ResponseDto("NE", "존재하지 않는 사용자입니다.");
        }
    }


}
