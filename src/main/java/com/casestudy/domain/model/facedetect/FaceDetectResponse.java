package com.casestudy.domain.model.facedetect;

public enum FaceDetectResponse {
    success,
    failed;

    public boolean isFailed() {
        return this == failed;
    }
}