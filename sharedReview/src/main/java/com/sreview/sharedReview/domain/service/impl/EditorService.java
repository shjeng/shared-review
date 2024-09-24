package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.customexception.CustomRuntimeException;
import com.sreview.sharedReview.domain.jpa.service.EditorRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditorService {
    private final EditorRepoService editorRepoService;

}
