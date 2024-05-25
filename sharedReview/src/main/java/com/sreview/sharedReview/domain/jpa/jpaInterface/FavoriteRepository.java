package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByBoard(Board board);

    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.board.boardId =:boardId AND f.user.email =:email")
    void deleteFavoriteByBoardIdEmail(@Param("boardId") Long boardId, @Param("email") String email);
}
