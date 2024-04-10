package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Tag;
import com.sreview.sharedReview.domain.jpa.jpaInterface.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TagRepoService {
    private final TagRepository tagRepository;

    @Transactional
    public void saveAll(List<Tag> tags){
        tagRepository.saveAll(tags);
    }

    public List<Tag> findAllByBoard(Board board) {
        return tagRepository.findAllByBoard(board);
    }
}
