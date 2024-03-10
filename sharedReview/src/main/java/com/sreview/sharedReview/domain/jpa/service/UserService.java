package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(User user) {
        userRepository.save(user);
        return user.getId();
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
//    public void validateDuplidateUser(User user) {
//        List<User> findUsers = userRepository.findByNickName(user.getNickname());
//        if (!findUsers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }

//    public List<User> findUser
    public Optional<User> findByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }
}
