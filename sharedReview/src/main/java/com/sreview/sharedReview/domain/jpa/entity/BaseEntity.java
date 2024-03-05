package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AutoCloseable.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    private LocalDateTime localDateTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
