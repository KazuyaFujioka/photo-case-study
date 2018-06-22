package com.casestudy.domain.model.adjustment;

import com.casestudy.domain.fundamental.Photo;

import java.awt.image.BufferedImage;

public class AdjustmentResponse {

    Photo adjustment;

    public AdjustmentResponse(Photo adjustment) {
        this.adjustment = adjustment;
    }

    public BufferedImage adjustmentImage() {
        return adjustment.bufferedImage();
    }

    AdjustmentResponse() {
    }
}