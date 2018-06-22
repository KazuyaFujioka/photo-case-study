package com.casestudy.infrastructure.datasource.adjustment;

import com.casestudy.domain.model.adjustment.AdjustmentSize;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Mat;

class AdjustmentHeight implements Adjustment {

    Mat destination;
    AdjustmentSize size;

    Ratio request;
    Ratio original;

    AdjustmentHeight(Mat destination, AdjustmentSize size, Ratio request, Ratio original) {
        this.destination = destination;
        this.size = size;
        this.request = request;
        this.original = original;
    }

    @Override
    public Size size() {
        return new Size(resizeWidth(), resizeHeight());
    }

    @Override
    public Mat roi() {
        Integer position = ((size.height() - resizeHeight()) / 2);
        Mat roi = new Mat(destination, new Rect(0, position, resizeWidth(), resizeHeight()));
        return roi;
    }

    private Integer resizeHeight() {
        return Math.round(request.width / original.rate());
    }

    private Integer resizeWidth() {
        return Math.round(request.width);
    }
}