package com.casestudy.domain.model.adjustment;

import com.casestudy.domain.fundamental.Photo;

import java.awt.image.BufferedImage;

public class AdjustmentRequest {

    Photo photo;
    AdjustmentSize size;

    public AdjustmentRequest(Photo photo, AdjustmentSize size) {
        this.photo = photo;
        this.size = size;
    }

    public BufferedImage photo() {
        return photo.bufferedImage();
    }

    public AdjustmentSize size() {
        return size;
    }

    AdjustmentRequest() {
    }
}