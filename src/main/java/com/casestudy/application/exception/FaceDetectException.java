package com.casestudy.application.exception;

public class FaceDetectException extends RuntimeException {

    public FaceDetectException(Exception exception) {
        super(exception);
    }
}