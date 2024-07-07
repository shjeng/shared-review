package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.common.customexception.NonExistBoardException;
import com.sreview.sharedReview.domain.dto.request.board.BoardRequestParam;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.jpaInterface.BoardRepository;
import com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo.BoardRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true) // 이건 읽기 전용
@Service
@RequiredArgsConstructor
public class BoardRepoService { // DB에 넣어주는 아이

    private final BoardRepository boardRepository;

    public List<Board> findLatestBoards() {
        return boardRepository.findLatestBoards();
    }
    public Page<Board> findLatestBoards(Pageable pageable) {
        return boardRepository.findLatestBoards(pageable);
    }

    public List<Board> findFavoriteBoardTop3() {
        return boardRepository.findFavoriteBoardTop3(LocalDateTime.now().minusWeeks(1));
    }
    public List<Board> findFavoriteBoardTop3(String month) {
        return boardRepository.findFavoriteBoardTop3(LocalDateTime.now().minusMonths(1));
    }

    @Transactional // 이 때 커밋이 나감.
    public void save(Board postEntity) {
        boardRepository.save(postEntity);
    }

    public Board findById(Long boardId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        optionalBoardChk(boardOptional);
        return boardOptional.get();
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findBoardById(boardId);

    }
//    public Optional<Board> findBoardAndCommentsUserById(Long boardId) {
//        return boardRepository.findBoardAndCommentsUserById(boardId);
//    }

    public Page<Board> findBoardsByParams(Pageable pageable, BoardRequestParam params){
        Long categoryId = params.getCategoryId();
        String searchType = params.getSearchType();
        String searchWord = params.getSearchWord();

        Page<Board> result = null;
        if (categoryId != null && searchType == null && searchWord == null) {
            result = boardRepository.findBoardsByCategory(categoryId, pageable);
        }
        if (searchType != null && searchWord != null) {
            if (searchType.equals("title")) {
                if (categoryId == null) {
                    result = boardRepository.findBoardsBySearchWord(searchWord, pageable);
                } else {
                    result = boardRepository.findBoardsByCategoryIdAndTitle(searchWord, categoryId, pageable);
                }
            }
        }

        return result;
    }

    public Page<Board> findBoardsByUserEmail(String userEmail, Pageable pageable){
        return boardRepository.findBoardsByUserEmail(userEmail, pageable);
    }
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    private void optionalBoardChk(Optional<Board> boardOptional){
        if (boardOptional.isEmpty()) {
            throw new NonExistBoardException("존재하지 않는 게시물입니다.");
        }
    }

    public List<Board> findByTitle(String inputValue) {
        return boardRepository.findByTitle(inputValue);
    }

    public List<Board> findByCategoryId(Long inputValue) {
        return boardRepository.findByCategoryId(inputValue);
    }


    public List<Board> findByUserNickname(String inputValue) {
        return boardRepository.findByUserNickname(inputValue);
    }


    public Page<Board> findList(BoardRequestParam boardRequestParam, Pageable pageable) {
        return boardRepository.findBoards(boardRequestParam, pageable);
    }
}
