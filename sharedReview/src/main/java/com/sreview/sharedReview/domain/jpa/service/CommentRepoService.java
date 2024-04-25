package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Comment;
import com.sreview.sharedReview.domain.jpa.jpaInterface.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Comment> findCommentsByBoard(Board board, Pageable pageable){return commentRepository.findCommentsByBoard(board, pageable);}
}
