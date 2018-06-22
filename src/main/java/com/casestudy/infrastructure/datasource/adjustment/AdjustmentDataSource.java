package com.casestudy.infrastructure.datasource.adjustment;

import com.casestudy.domain.model.adjustment.*;
import com.casestudy.infrastructure.datasource.Temporary;
import com.casestudy.infrastructure.datasource.Workingplace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Size;

import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_core.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Repository
class AdjustmentDataSource implements AdjustmentRepository {

    Boolean debugging;

    @Override
    public AdjustmentResponse resize(AdjustmentRequest request) {

        Workingplace.create();
        Temporary temporary = new Temporary(request.photo());
        temporary.write();

        Mat source = temporary.source();
        AdjustmentSize size = request.size();

        try {
            Resize resize = new Resize(source, size);
            resize.resizing();

            if (debugging)
                resize.write();

            return new AdjustmentResponse(resize.asImage(), resize.status);
        } catch (Exception e) {
            return new AdjustmentResponse(AdjustmentStatus.failed);
        } finally {
            temporary.remove();
        }
    }

    AdjustmentDataSource(@Value("${haarcascades.debugging:false}") Boolean debugging) {
        this.debugging = debugging;
    }
}