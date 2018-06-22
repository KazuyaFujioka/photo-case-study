package com.casestudy.domain.model.facedetect;

import com.casestudy.domain.fundamental.Photo;

import java.awt.image.BufferedImage;

public class FaceDetectRequest {

    Photo photo;

    public FaceDetectRequest(Photo photo) {
        this.photo = photo;
    }

    public BufferedImage photo() {
        return photo.bufferedImage();
    }

    FaceDetectRequest() {
    }
}