package com.casestudy.application.exception;

public class AdjustmentException extends RuntimeException {

    public AdjustmentException(Exception exception) {
        super(exception);
    }
}