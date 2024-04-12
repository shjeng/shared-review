package com.sreview.sharedReview.domain.init;

import com.sreview.sharedReview.domain.dto.request.auth.SignUpRequest;
import com.sreview.sharedReview.domain.jpa.entity.*;
import com.sreview.sharedReview.domain.jpa.jpaInterface.UserRepository;
import com.sreview.sharedReview.domain.jpa.service.BoardRepoService;
import com.sreview.sharedReview.domain.jpa.service.CategoryRepoService;
import com.sreview.sharedReview.domain.jpa.service.CommentRepoService;
import com.sreview.sharedReview.domain.jpa.service.FavoriteRepoService;
import com.sreview.sharedReview.domain.service.impl.AuthServiceImpl;
import com.sreview.sharedReview.domain.util.MarkdownUtil;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Init {

    private final AuthServiceImpl authService;
    private final CategoryRepoService categoryRepoService;
    private final UserRepository userRepository;
    private final BoardRepoService boardRepoService;
    private final CommentRepoService commentRepoService;
    private final FavoriteRepoService favoriteRepoService;
    private final MarkdownUtil markdownUtil;
    @PostConstruct // 의존성 주입이 완료된 후 실행되어야 하는 메서드
    public void init(){
        userInit();
        categoryInit();
        boardInit();
        commentInit();
        favoriteInit();

    }

    public void userInit(){
        SignUpRequest userRequest = new SignUpRequest();
        userRequest.setEmail("test@naver.com");
        userRequest.setNickname("testNick");
        userRequest.setPassword("123");
        authService.signUp(userRequest);
    }
    public void categoryInit(){
        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        User user = userOptional.get();
        Category cate1 = new Category("컴퓨터", user);
        Category cate2 = new Category("미용", user);
        categoryRepoService.save(cate1);
        categoryRepoService.save(cate2);
    }
    public void boardInit(){
        Optional<Category> category = categoryRepoService.findByName("컴퓨터");
        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        User user = userOptional.get();
        Board board = new Board();
        board.setTitleContent("제목","<div>내용대충</div>");
        board.setUserAndCategory(user, category.get());

        boardRepoService.save(board);
    }
    public void commentInit(){
        Optional<Board> boardOptional = boardRepoService.findById(1L);
        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        Comment comment = new Comment();
        comment.setUserBoardContent(userOptional.get(), boardOptional.get(), "댓글입니다~");
        commentRepoService.save(comment);
    }
    public void favoriteInit(){
        Optional<Board> boardOptional = boardRepoService.findById(1L);
        Optional<User> userOptional = userRepository.findByEmail("test@naver.com");
        Favorite favorite = Favorite.builder()
                .user(userOptional.get())
                .board(boardOptional.get())
                .build();
        favoriteRepoService.save(favorite);
    }
}
