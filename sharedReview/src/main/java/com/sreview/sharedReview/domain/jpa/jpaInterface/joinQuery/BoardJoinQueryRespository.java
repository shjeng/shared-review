package com.sreview.sharedReview.domain.jpa.jpaInterface.joinQuery;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJoinQueryRespository extends JpaRepository<Board, Long> {
}
