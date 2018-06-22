package com.casestudy.infrastructure.datasource.adjustment;

import com.casestudy.domain.model.adjustment.AdjustmentSize;
import com.casestudy.domain.model.adjustment.AdjustmentStatus;
import com.casestudy.domain.model.facedetect.FaceDetectResponse;
import com.casestudy.infrastructure.datasource.Temporary;
import com.casestudy.infrastructure.datasource.Workingplace;

import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.Mat;

import static org.bytedeco.javacpp.opencv_core.CV_8UC3;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Resize {

    Mat source;
    AdjustmentSize size;

    Mat destination;

    AdjustmentStatus status = AdjustmentStatus.failed;

    Resize(Mat source, AdjustmentSize size) {
        this.source = source;
        this.size = size;
    }

    void resizing() {
        Ratio request = new Ratio(size.width(), size.height());
        Ratio original = new Ratio(source.cols(), source.rows());
        destination = new Mat(new Size(size.width(), size.height()), CV_8UC3, Scalar.WHITE);

        Mat temporary = new Mat();
        Adjustment adjustment = Adjustment.create(destination, size, request, original);

        resize(source, temporary, adjustment.size());

        Mat roi = adjustment.roi();
        temporary.copyTo(roi);
    }

    BufferedImage asImage() {
        Workingplace debugging = Workingplace.forDebugging();
        debugging.create();
        File file = new File(debugging.path() + File.separator + Temporary.fileName());
        imwrite(file.getPath(), destination);

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            status = AdjustmentStatus.success;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            file.deleteOnExit();
        }
        return bufferedImage;
    }

    void write() {
        Workingplace debugging = Workingplace.forDebugging();
        debugging.create();
        String file = Temporary.fileName();
        imwrite(debugging.path() + File.separator + file, destination);
    }

    AdjustmentStatus status() {
        return status;
    }
}