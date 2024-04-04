package com.sreview.sharedReview.domain.common;

public interface ResponseCode {
    // HTTP Stauts 200
    String SUCCESS = "SU";

    // HTTP Stauts 400
    String VALIDATION_FAIL = "VF"; // 유효성 검사 실패
    String DUPLICATE_EMAIL = "DM"; // 이메일 중복
    String DUPLICATE_NICKNAME = "DN"; // 중복 닉네임
    String NON_EXISTED_USER = "NU"; // 존재하지 않는 유저
    String NON_EXISTED_BOARD = "NB"; // 존재하지 않는 유저
    String NON_EXISTED_CATEGORY = "NC"; // 존재하지 않는 카테고리
    String SIGN_IN_FAIL = "SF"; // 로그인 실패
    String CERTIFICATION_FAIL = "CF";
    String DUPLICATE_CATEGORY = "DC";
    String AUTH_NUMBER_FAILED = "ANF"; // 인증번호 불일치

    // HTTP Stauts 500
    String MAIL_FAIL = "MF"; // 메일 전송 실패
    String DATABASE_ERROR = "DBE"; // 인터넷 오류
}
