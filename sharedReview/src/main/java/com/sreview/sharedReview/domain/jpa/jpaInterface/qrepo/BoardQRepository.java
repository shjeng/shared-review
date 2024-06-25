package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findListByKeyword(String keyword, Long categoryId) {
        List<Board> fetch = queryFactory.select(QBoard.board).from(QBoard.board).fetch();
//        queryFactory.select(Q)
        return fetch;
    }
}
