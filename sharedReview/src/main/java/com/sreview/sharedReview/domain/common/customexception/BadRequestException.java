package com.sreview.sharedReview.domain.common.customexception;

public class BadRequestException extends CustomRuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
