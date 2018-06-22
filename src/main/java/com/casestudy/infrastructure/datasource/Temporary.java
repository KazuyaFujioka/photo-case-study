package com.casestudy.infrastructure.datasource;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Temporary {

    static final String extension = "jpg";
    final File file = new File(Workingplace.directory.getPath() + File.separator + fileName());

    BufferedImage bufferedImage;

    public Temporary(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public void write() {
        try {
            ImageIO.write(bufferedImage, extension, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove() {
        file.deleteOnExit();
    }

    public Mat source() {
        String photo = file.getPath();
        Mat source = imread(photo);
        return source;
    }

    public String path() {
        return file.getPath();
    }

    public static String fileName() {
        return new String("temporary" + System.currentTimeMillis() + "." + extension);
    }

    Temporary() {
    }
}