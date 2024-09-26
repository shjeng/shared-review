package com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sreview.sharedReview.domain.jpa.entity.EditorImage;
import com.sreview.sharedReview.domain.jpa.entity.QEditorImage;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sreview.sharedReview.domain.jpa.entity.QEditorImage.editorImage;

@Repository
public class EditorRepositoryImpl implements EditorRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public EditorRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<EditorImage> findByIds(List<Long> ids) {
        return jpaQueryFactory.select(editorImage)
                .from(editorImage)
                .where(editorImage.id.in(ids))
                .fetch();
    }
}
