package com.sreview.sharedReview.domain.init;

import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.jpa.entity.*;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import com.sreview.sharedReview.domain.jpa.service.BoardRepoService;
import com.sreview.sharedReview.domain.jpa.service.CategoryRepoService;
import com.sreview.sharedReview.domain.jpa.service.CommentRepoService;
import com.sreview.sharedReview.domain.jpa.service.FavoriteRepoService;
import com.sreview.sharedReview.domain.service.impl.AuthServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class Init {

    private final AuthServiceImpl authService;
    private final CategoryRepoService categoryRepoService;
    private final UserRepository userRepository;
    private final BoardRepoService boardRepoService;
    private final CommentRepoService commentRepoService;
    private final FavoriteRepoService favoriteRepoService;
    private final EntityManager em;
    @PostConstruct // 의존성 주입이 완료된 후 실행되어야 하는 메서드
    public void init(){
        SignUpRequest userRequest = new SignUpRequest();
        userRequest.setEmail("test@naver.com");
        userRequest.setNickname("testNick");
        userRequest.setPassword("123");
        authService.signUp(userRequest);

        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        User user = userOptional.get();

        Category cate1 = new Category("컴퓨터", user);
        Category cate2 = new Category("미용", user);
        categoryRepoService.save(cate1);
        categoryRepoService.save(cate2);
        Board board = Board.builder()
                .title("제목입니다.")
                .content("<div>내용입니다.<div>")
                .categorie(cate1)
                .user(user)
                .build();
        boardRepoService.save(board);
        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .content("댓글입니다.")
                .build();
        commentRepoService.save(comment);

        Favorite favorite = Favorite.builder()
                .board(board)
                .user(user)
                .build();
        favoriteRepoService.save(favorite);
    }

}
