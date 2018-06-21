package com.casestudy.domain.model.facedetect;

import java.awt.image.BufferedImage;

public class FaceDetectRequest {

    BufferedImage photo;

    public FaceDetectRequest(BufferedImage photo) {
        this.photo = photo;
    }

    public BufferedImage photo() {
        return photo;
    }

    FaceDetectRequest() {
    }
}