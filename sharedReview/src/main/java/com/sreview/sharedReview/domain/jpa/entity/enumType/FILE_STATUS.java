package com.sreview.sharedReview.domain.jpa.entity.enumType;

public enum FILE_STATUS {
    PROFILE("프로필 사진"), BOARD("게시판 파일"), NORMAL("저장"), TEMP("임시저장");

    private final String description;

    FILE_STATUS(String description) {
        this.description = description;
    }
}
