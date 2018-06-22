package com.casestudy.domain.model.adjustment;

import java.awt.image.BufferedImage;

public class AdjustmentResponse {

    BufferedImage adjustmentImage;
    AdjustmentStatus status;

    public AdjustmentResponse(BufferedImage adjustmentImage, AdjustmentStatus status) {
        this.adjustmentImage = adjustmentImage;
        this.status = status;
    }

    public boolean isFailed() {
        return status.isFailed();
    }

    public BufferedImage adjustmentImage() {
        return adjustmentImage;
    }

    public AdjustmentResponse(AdjustmentStatus status) {
        this.status = status;
    }

    AdjustmentResponse() {
    }
}
