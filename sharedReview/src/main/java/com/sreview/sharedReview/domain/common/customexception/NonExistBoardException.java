package com.sreview.sharedReview.domain.common.customexception;

public class NonExistBoardException extends RuntimeException{

    public NonExistBoardException(String message) {
        super(message);
    }
}
