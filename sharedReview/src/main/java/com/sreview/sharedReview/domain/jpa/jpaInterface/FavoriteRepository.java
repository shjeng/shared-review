package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByBoard(Board board);

    @Query("SELECT COUNT(*) FROM Favorite f WHERE f.board.boardId =:boardId")
    Integer countByBoardId(@Param("boardId") Long boardId);

    // Favorite 테이블에 해당 boardID 컬럼 삭제
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.board.boardId =:boardId AND f.user.email =:email")
    Integer deleteFavoriteByBoardIdEmail(@Param("boardId") Long boardId, @Param("email") String email);

    // 카운터 감소
    @Modifying
    @Query("UPDATE Board b SET b.favoriteCount = b.favoriteCount - 1 WHERE b.id = :boardId")
    void decrementFavoriteCount(@Param("boardId") Long boardId);

    @Query("SELECT f FROM Favorite f WHERE f.board.boardId =:boardId AND f.user.email =:email")
    Optional<Favorite> findByBoardIdEmail(@Param("boardId") Long boardId, @Param("email") String email);

    // 카운터 증가
    @Modifying
    @Query("UPDATE Board b SET b.favoriteCount = b.favoriteCount + 1 WHERE b.id = :boardId")
    void incrementFavoriteCount(@Param("boardId") Long boardId);

}
