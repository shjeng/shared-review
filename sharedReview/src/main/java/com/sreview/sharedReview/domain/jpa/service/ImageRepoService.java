package com.sreview.sharedReview.domain.jpa.service;

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

//    @Transactional
//    public void saveAll(Long boardId, String imageUrl) {
//        // Image 객체를 생성하여 저장
//        Image image = new Image();
//        image.setBoardId(boardId);
//        image.setImageUrl(imageUrl);
//
//        imageRepository.saveAll(Collections.singletonList(image));
//    }
}
