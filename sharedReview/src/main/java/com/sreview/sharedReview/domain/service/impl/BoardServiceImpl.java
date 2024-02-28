package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.BoardRepoService;
import com.sreview.sharedReview.domain.jpa.service.CategoryService;
import com.sreview.sharedReview.domain.jpa.service.UserService;
import com.sreview.sharedReview.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepoService boardRepoService;
    private final CategoryService categoryService;
    private final UserService userService;
//    @Override
//    public void savePost(PostDTO postDTO) {
//        try{
//            //
//            // 기능들 작성
//            //
//            Board post = new Board();
//            BeanUtils.copyProperties(postDTO, post);
//
//            postService.save(post);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public ResponseEntity<? super CategoryWriteResponse> categorySave(CategoryWriteRequest request) {
        try{
            String email = "";
            Optional<User> userOptional = userService.findByEmail(email);
            if(userOptional.isEmpty()) return CategoryWriteResponse.notExistedUser();
            User user = userOptional.get();

            String getName = request.getName();
            Category category = new Category(getName,user);

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request) {
        try {

        } catch (Exception e){
            e.printStackTrace();
            BoardWriteResponse.databaseError();
        }
        return BoardWriteResponse.success();
    }
}



