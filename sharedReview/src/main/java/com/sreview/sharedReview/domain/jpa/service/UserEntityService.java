package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.common.customexception.NonExistUserException;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserEntityService {

    private final UserRepository userRepository;
    private final JavaMailSender emailSender;


    @Transactional
    public Long save(User user) {
        userRepository.save(user);
        return user.getId();
    }
    public User findByEmail(String email){
        return optionalCheck(userRepository.findByEmail(email));
    }

    public Optional<User> passwordCheck(String email, String password) {
        return userRepository.passwordCheck(email, password);
    }
    public Optional<User> existCheckEmail(String email){
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

    public void sendVerificationEmail(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("이메일 인증 코드");
        message.setText("인증 코드: " + verificationCode);

        emailSender.send(message);
    }

    public static String generateVerificationCode() {
        // 6자리 난수 생성
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public User optionalCheck(Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            throw new NonExistUserException();
        }
        return optionalUser.get();
    }
}
