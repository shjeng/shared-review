package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Comment;
import com.sreview.sharedReview.domain.jpa.jpaInterface.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentRepoService {

    private final CommentRepository commentRepository;

    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
