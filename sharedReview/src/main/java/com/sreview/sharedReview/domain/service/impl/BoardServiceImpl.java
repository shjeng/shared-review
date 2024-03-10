package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.Tag;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.service.BoardRepoService;
import com.sreview.sharedReview.domain.jpa.service.CategoryRepoService;
import com.sreview.sharedReview.domain.jpa.service.TagRepoService;
import com.sreview.sharedReview.domain.jpa.service.UserService;
import com.sreview.sharedReview.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepoService boardRepoService;
    private final CategoryRepoService categoryRepoService;
    private final UserService userService;
    private final TagRepoService tagRepoService;
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
    public ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request) {
        try{
            String email = "";
            Optional<User> userOptional = userService.findByEmail(email);
            if(userOptional.isEmpty()) return CategoryWriteResponse.notExistedUser();
            User user = userOptional.get();

            String getName = request.getName();
            Category category = new Category(getName,user);
            categoryRepoService.save(category);
        }catch (Exception e){
            e.printStackTrace();

        }
        return CategoryWriteResponse.success();
    }

    @Override
    public ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request,String email) {
        try {
            Optional<User> userOptional = userService.findByEmail(email);
            if(userOptional.isEmpty()) return BoardWriteResponse.notExistedUser();

            Category category = categoryRepoService.findByName(request.getCategory()).get();
            User user = userOptional.get();
            Board board = BoardWriteRequest.getBoard(request);
            board.setUserAndCategory(user,category);
            List<Tag> tagList = request.getTagList();
            tagRepoService.saveAll(tagList);

            boardRepoService.save(board);
        } catch (Exception e){
            e.printStackTrace();
            BoardWriteResponse.databaseError();
        }
        return BoardWriteResponse.success();
    }
}



