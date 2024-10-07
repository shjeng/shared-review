package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sreview.sharedReview.domain.dto.request.board.BoardRequestParam;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.QEditorImage;
import com.sreview.sharedReview.domain.jpa.entity.QFavorite;
import com.sreview.sharedReview.domain.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.sreview.sharedReview.domain.jpa.entity.QBoard.*;
import static com.sreview.sharedReview.domain.jpa.entity.QEditorImage.editorImage;
import static com.sreview.sharedReview.domain.jpa.entity.QFavorite.favorite;

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
        QEditorImage subEditor = new QEditorImage("subEditor");
        List<Board> content = jpaQueryFactory
                .select(board)
                .from(board)
                .leftJoin(editorImage).on(
                        editorImage.boardId.eq(board).and(
                        editorImage.id.eq(
                                JPAExpressions.select(subEditor.id.min()).from(subEditor).where(subEditor.boardId.eq(board))
                        )))
                .where(searchWordEq(boardRequestParam), categoryIdEq(boardRequestParam))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(board.createDate.desc()).fetch();
        JPAQuery<Long> count = jpaQueryFactory.select(board.count()).from(board).where(searchWordEq(boardRequestParam), categoryIdEq(boardRequestParam));
        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    /*
    * 검색어와 검색어 타입을 받아서 결과를 반환
    * */
    private BooleanExpression searchWordEq(BoardRequestParam requestParam) {
        String searchType = requestParam.getSearchType();
        String searchWord = requestParam.getSearchWord();
        /*검색어 타입과 검색어가 둘 다 없는 경우 */
        if (searchType == null || searchWord == null) {
            return null;
        }
        /*검색 타입이 title인 경우 */
        if (searchType.equals("title")) {
            return board.title.contains(searchWord);
        } else if (searchType.equals("content")) {
            /* 검색 타입이 내용인 경우 */
            return board.content.contains(searchWord);
        } else if (searchType.equals("writer")) {
            return board.user.email.contains(searchWord);
        } else {
            /* 검색 타입이 전체인 경우 */
            return board.content.contains(searchWord).or(board.title.contains(searchWord)).or(board.user.email.contains(searchWord));
        }

    }
    /*카테고리 아이디를 가지고 게시물을 찾음.*/
     private BooleanExpression categoryIdEq(BoardRequestParam requestParam) {
         Long categoryId = requestParam.getCategoryId();
         if (categoryId == null) {
             return null;
         }
         return board.category.id.eq(categoryId);
     }

     @Override
     public Board findBoardById(Long boardId) {
         return jpaQueryFactory.select(board)
                 .from(board)
                 .leftJoin(favorite)
                 .on(board.boardId.eq(favorite.id))
                 .fetchJoin()
                 .where(board.boardId.eq(boardId))
                 .fetchOne();
     }
    @Override
    public void update() {
    }

}

