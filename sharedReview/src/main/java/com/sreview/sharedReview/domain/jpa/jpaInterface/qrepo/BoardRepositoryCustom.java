package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.sreview.sharedReview.domain.dto.request.board.BoardRequestParam;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<Board> findBoards(BoardRequestParam boardRequestParam, Pageable pageable);


    void update();
}
