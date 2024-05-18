package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적인 쿼리 메서드가 필요하다면 작성

    //    @Query("select b from Board b join fetch b.comments c where b.boardId =:boardId")
//    Optional<Board> findBoardAndCommentsUserById(@Param("boardId") Long boardId);
    Page<Board> findAll(Pageable pageable);
    List<Board> findAll();

    @Query("select b from Board b order by b.createDate desc limit 10")
    List<Board> findLatestBoards();
    @Query("select b from Board b")
    Page<Board> findLatestBoards(Pageable pageable);

    @Query("select b from Board b where b.user.email =:email")
    Page<Board> findBoardsByUserEmail(@Param("email") String userEmail, Pageable pageable);
    @Query("select b from Board b where b.createDate >= :weekAgo order by b.favoriteCount desc, b.createDate desc limit 10")
    List<Board> findFavoriteBoardTop3(@Param("weekAgo") LocalDateTime weekAgo);

    @Query("SELECT b FROM Board b where b.title = :title")
    List<Board> findByTitle(@Param("title") String boardTitle);

    @Query("SELECT b FROM Board b where b.user.nickname = :nickname")
    List<Board> findByUserNickname(@Param("nickname") String boardTitle);
//    @Query("select b from Board b left join fetch b.comments c left join fetch c.user where b.boardId =:boardId")
//    Optional<Board> findBoardAndCommentsUserById(@Param("boardId") Long boardId);
}
