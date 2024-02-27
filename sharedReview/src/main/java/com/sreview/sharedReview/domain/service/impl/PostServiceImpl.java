package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.board.dto.PostDTO;
import com.sreview.sharedReview.domain.board.entity.PostEntity;
import com.sreview.sharedReview.domain.jpa.jpaInterface.PostRepository;
import com.sreview.sharedReview.domain.jpa.service.PostService;
import com.sreview.sharedReview.domain.service.PostSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostSerivce {
    private final PostService postService;

    @Override
    public void savePost(PostDTO postDTO) {
        try{
            //
            // 기능들 작성
            //
            PostEntity post = new PostEntity();
            BeanUtils.copyProperties(postDTO, post);

            postService.save(post);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}



