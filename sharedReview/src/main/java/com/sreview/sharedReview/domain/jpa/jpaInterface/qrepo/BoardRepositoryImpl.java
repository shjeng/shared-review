package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sreview.sharedReview.domain.dto.request.board.BoardRequestParam;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sreview.sharedReview.domain.jpa.entity.QBoard.*;

/*
 * custom repository 작성
 * Impl 붙여서 custom repository 구현
 * 기존 repository에도 custom repository 상속
 * */
@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Board> findBoards(BoardRequestParam boardRequestParam, Pageable pageable) {
        jpaQueryFactory.select(board).from(board).where(board.title.like(boardRequestParam.getSearchWord()));
        return null;
    }

    private BooleanExpression searchWordEq(BoardRequestParam requestParam) {
        String searchType = requestParam.getSearchType();
        String searchWord = requestParam.getSearchWord();
        if (searchType == null || searchWord == null) {
            return null;
        }
        if (searchType.equals("title")) {
            return board.title.like(searchWord);
        } else if (searchType.equals("content")) {
            return board.content.like(searchWord);
        } else {
            return board.content.like(searchWord).or(board.title.like(searchWord));
        }

    }

    @Override
    public void update() {
    }

}

