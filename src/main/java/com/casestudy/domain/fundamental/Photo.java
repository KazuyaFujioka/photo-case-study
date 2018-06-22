package com.casestudy.domain.fundamental;

import java.awt.image.BufferedImage;

public class Photo {

    BufferedImage value;

    public Photo(BufferedImage value) {
        this.value = value;
    }

    public BufferedImage bufferedImage() {
        return value;
    }

    Photo() {
    }
}