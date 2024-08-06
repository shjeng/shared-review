package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT c FROM Comment c WHERE c.board =:board AND c.deleteStatus = 'N'")
    Page<Comment> findCommentsByBoard(@Param("board") Board board, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.board.boardId =:boardId AND c.deleteStatus = 'N'")
    Page<Comment> findCommentsByBoardId(@Param("boardId") Long boardId, Pageable pageable);


    // 댓글 작성시 사용
    @Modifying
    @Query("UPDATE Board b SET b.commentCount = :commentCount WHERE b.boardId = :boardId")
    void updateCommentCount(@Param("boardId") Long boardId, @Param("commentCount") long commentCount);

    // 처음 조회시 사용
    @Modifying
    @Query("UPDATE Board b SET b.commentCount = b.commentCount + 1 WHERE b.boardId = :boardId")
    void updateCommentCount(@Param("boardId") Long boardId);

    // 댓글 갯수 감소
    @Modifying
    @Query("UPDATE Board b SET b.commentCount = b.commentCount - 1 WHERE b.id = :boardId")
    void decrementCommentCount(@Param("boardId") Long boardId);
}
