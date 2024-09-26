package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.EditorImage;
import com.sreview.sharedReview.domain.jpa.jpaInterface.qrepo.EditorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditorRepository extends JpaRepository<EditorImage, Long> , EditorRepositoryCustom {

}
