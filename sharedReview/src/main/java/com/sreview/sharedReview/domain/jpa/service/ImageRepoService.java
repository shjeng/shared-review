package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Image;
import com.sreview.sharedReview.domain.jpa.jpaInterface.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageRepoService {
    private final ImageRepository imageRepository;

    @Transactional
    public void saveAll(Board boardId, String imageUrl) {
        Image image = new Image();
        image.setBoardIdAndImageUrl(boardId,imageUrl);
        imageRepository.saveAll(Collections.singletonList(image));
    }
}
