package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.board.dto.PostDTO;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl extends BoardService {

    private final BoardService boardService;

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
}



