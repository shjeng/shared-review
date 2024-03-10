package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.BoardTag;
import com.sreview.sharedReview.domain.jpa.jpaInterface.BoardTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardTagRepoService {
    private final BoardTagRepository boardTagRepository;
    public void saveAll(List<BoardTag> boardTags){
        boardTagRepository.saveAll(boardTags);
    }
}
