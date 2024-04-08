package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
