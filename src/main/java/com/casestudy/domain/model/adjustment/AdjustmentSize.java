package com.casestudy.domain.model.adjustment;

public class AdjustmentSize {

    Integer height;
    Integer width;

    public AdjustmentSize(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Integer height() {
        return height;
    }

    public Integer width() {
        return width;
    }

    AdjustmentSize() {
    }
}
