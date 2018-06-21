package com.casestudy.domain.model.facedetect;

public interface FaceDetectRepository {
    FaceDetectResponse detect(FaceDetectRequest request);
}
