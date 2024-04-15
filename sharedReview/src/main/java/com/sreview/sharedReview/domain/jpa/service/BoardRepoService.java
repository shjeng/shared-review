package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.jpaInterface.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true) // 이건 읽기 전용
@Service
@RequiredArgsConstructor
public class BoardRepoService { // DB에 넣어주는 아이

    private final BoardRepository boardRepository;


    public List<Board> findLatestBoards(){
        return boardRepository.findLatestBoards();
    }
    @Transactional // 이 때 커밋이 나감.
    public void save(Board postEntity){
        boardRepository.save(postEntity);
    }

    public Optional<Board> findById(Long boardId){
        return boardRepository.findById(boardId);
    }
    public Optional<Board> findBoardAndCommentsUserById(Long boardId){
        return boardRepository.findBoardAndCommentsUserById(boardId);
    }
}
