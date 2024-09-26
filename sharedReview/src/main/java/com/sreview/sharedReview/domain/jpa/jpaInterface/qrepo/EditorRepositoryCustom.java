package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.sreview.sharedReview.domain.jpa.entity.EditorImage;

import java.util.List;

public interface EditorRepositoryCustom {

    List<EditorImage> findByIds(List<Long> ids);
}
