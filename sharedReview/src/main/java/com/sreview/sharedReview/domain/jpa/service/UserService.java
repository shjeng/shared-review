package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.board.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(User user) {
//        validateDuplidateUser(user); // 중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

//    public void validateDuplidateUser(User user) {
//        List<User> findUsers = userRepository.findByNickName(user.getNickname());
//        if (!findUsers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }

    public List<User> findUser
}
