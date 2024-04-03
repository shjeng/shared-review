package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적인 쿼리 메서드가 필요하다면 작성

//    @Query("select b from Board b join fetch b.comments c where b.boardId =:boardId")
//    Optional<Board> findBoardAndCommentsById(@Param("boardId") Long boardId);
    @Query("select b from Board b left join fetch b.comments c  where b.boardId =:boardId")
    Optional<Board> findBoardAndCommentsById(@Param("boardId") Long boardId);
}
