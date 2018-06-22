package com.casestudy.infrastructure.datasource.adjustment;

class Ratio {

    Float width;
    Float height;

    Ratio(Integer width, Integer height) {
        this.width = Float.valueOf(width);
        this.height = Float.valueOf(height);
    }

    Float rate() {
        return width / height;
    }

    boolean isLarge(Ratio ratio) {
        return rate() > ratio.rate();
    }
}