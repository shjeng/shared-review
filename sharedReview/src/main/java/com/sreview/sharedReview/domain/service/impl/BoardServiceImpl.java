package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.board.dto.PostDto;
import com.sreview.sharedReview.domain.board.entity.Post;
import com.sreview.sharedReview.domain.board.repository.PostRepository;
import com.sreview.sharedReview.domain.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void save(PostDto postDto) {
        Post post = Post.fromDto(postDto);
        postRepository.save(post);
    }

    // 기타 필요한 메서드들...
}