package com.sreview.sharedReview.domain.init;

import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import com.sreview.sharedReview.domain.jpa.service.CategoryRepoService;
import com.sreview.sharedReview.domain.service.impl.AuthServiceImpl;
import com.sreview.sharedReview.domain.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Init {

    private final AuthServiceImpl authService;
    private final CategoryRepoService categoryRepoService;
    private final UserRepository userRepository;

    @PostConstruct // 의존성 주입이 완료된 후 실행되어야 하는 메서드
    private void init(){
        SignUpRequest userRequest = new SignUpRequest();
        userRequest.setEmail("test@naver.com");
        userRequest.setNickname("testNick");
        userRequest.setPassword("123");
        authService.signUp(userRequest);

        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        User user = userOptional.get();

        Category cate1 = new Category("컴퓨터", user);
        Category cate2 = new Category("미용", user);
        List<Category> categorys = new ArrayList<>();
        categorys.add(cate1);
        categorys.add(cate2);
        categoryRepoService.saveAll(categorys);
    }
}
