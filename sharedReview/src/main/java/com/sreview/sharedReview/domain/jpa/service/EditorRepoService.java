package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.common.customexception.CustomRuntimeException;
import com.sreview.sharedReview.domain.jpa.entity.EditorImage;
import com.sreview.sharedReview.domain.jpa.jpaInterface.EditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EditorRepoService {
    private final EditorRepository editorRepository;

    @Transactional
    public EditorImage saveEditorImage(EditorImage editorImage){
        editorRepository.save(editorImage);
        if (editorImage.getId() == null) {
            throw new CustomRuntimeException("엔티티 저장 실패");
        }
        return editorImage;
    }
}
