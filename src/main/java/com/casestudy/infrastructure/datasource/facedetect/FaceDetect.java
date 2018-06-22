package com.casestudy.infrastructure.datasource.facedetect;

import com.casestudy.domain.model.facedetect.FaceDetectResponse;
import com.casestudy.infrastructure.datasource.Temporary;
import com.casestudy.infrastructure.datasource.Workingplace;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;

class FaceDetect {

    Logger LOG = LoggerFactory.getLogger(FaceDetect.class);

    // 顔認識結果
    RectVector faceDetections = new RectVector();

    Temporary temporary;
    CascadeClassifier faceDetector;

    FaceDetectResponse response = FaceDetectResponse.failed;

    FaceDetect(Temporary temporary, CascadeClassifier faceDetector) {
        this.temporary = temporary;
        this.faceDetector = faceDetector;
    }

    void detection() {
        Mat source = temporary.source();

        Mat sourceGray = new Mat();
        cvtColor(source, sourceGray, COLOR_BGRA2GRAY);

        // 顔認識実行
        double scaleFactor = 1.2;
        int minNeighbors = 2;
        int flags = 0;
        Size minSize = new Size(50);
        Size maxSize = new Size(50);
        faceDetector.detectMultiScale(sourceGray, faceDetections,
                scaleFactor,
                minNeighbors,
                flags,
                minSize,
                maxSize);

        if(!faceDetections.empty())
            response = FaceDetectResponse.success;
    }

    void write() {
        if(response.isFailed()) return;

        Mat source = temporary.source();
        for(Rect r : faceDetections.get()) {
            LOG.info(r.toString());
            int x = r.x(), y = r.y(), h = r.height(), w = r.width();
            // 顔認識された部分を赤枠で囲む
            rectangle(source,
                    new opencv_core.Point(x, y),
                    new opencv_core.Point(x + w, y + h),
                    new opencv_core.Scalar(0, 0, 255, 0));
        }
        Workingplace debugging = Workingplace.forDebugging();
        debugging.create();

        String file = Temporary.fileName();
        LOG.info("debugging image=" + file);
        imwrite(debugging.path() + File.separator + file, source);
    }

    FaceDetectResponse response() {
        return response;
    }
}