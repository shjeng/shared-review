package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT c FROM Comment c WHERE c.board =:board")
    Page<Comment> findCommentsByBoard(@Param("board") Board board, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.board.boardId =:boardId")
    Page<Comment> findCommentsByBoardId(@Param("boardId") Long boardId, Pageable pageable);

}
