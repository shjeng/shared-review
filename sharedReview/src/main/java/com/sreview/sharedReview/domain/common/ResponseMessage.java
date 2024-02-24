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

    // 500
    String MAIL_FAIL = "Mail send failed.";
    String DATABASE_ERROR = "Databse error.";

}
