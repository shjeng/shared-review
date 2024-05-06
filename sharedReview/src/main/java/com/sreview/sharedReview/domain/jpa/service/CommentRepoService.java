package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Comment;
import com.sreview.sharedReview.domain.jpa.jpaInterface.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Page<Comment> findCommentsByBoardId(Long boardId, Pageable pageable){return commentRepository.findCommentsByBoardId(boardId, pageable);}
    @Transactional
    public void updateDeleteStatusY(Long commentId, String email){
        Optional<Comment> byId = commentRepository.findById(commentId);
        Comment comment = optinalChk(byId);
        comment.delete();
    }

    private Comment optinalChk(Optional<Comment> optional){
        if (optional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 댓글입니다.");
        }
        return optional.get();
    }
}
