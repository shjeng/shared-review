package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.PostEntity;
import com.sreview.sharedReview.domain.jpa.jpaInterface.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) // 이건 읽기 전용
@Service
@RequiredArgsConstructor
public class PostService { // DB에 넣어주는 아이

    private final PostRepository postRepository;

    @Transactional // 이 때 커밋이 나감.
    public void save(PostEntity postEntity){
        postRepository.save(postEntity);
    }
}
