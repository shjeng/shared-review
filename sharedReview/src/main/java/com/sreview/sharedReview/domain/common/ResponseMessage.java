package com.sreview.sharedReview.domain.common;

public interface ResponseMessage {
    // 200
    String SUCCESS = "Success.";

    // 400
    String VALIDATION_FAIL = "Validation faild.";
    String DUPLICATE_ID = "Duplicate Id.";
    String DUPLICATE_EMAIL = "Duplicate Email.";
    String DUPLICATE_NICKNAME = "Duplicate Nickname.";
    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "Certification faild.";

    String BAD_REQUEST = "Bad Request.";
    String NON_EXISTED_CATEGORY = "Non existed category"; // 존재하지 않는 카테고리
    String NON_EXISTED_USER = "Non existed user";
    String NON_EXISTED_BOARD = "Non existed board";
    
    String DUPLICATE_CATEGORY = "Duplicate cateogry";
    String AUTH_NUMBER_FAILED = "Verification code does NON match.";

    // 500
    String MAIL_FAIL = "Mail send failed.";
    String DATABASE_ERROR = "Databse error.";

}
