package com.casestudy.infrastructure.datasource.facedetect;

import com.casestudy.application.exception.FaceDetectException;
import com.casestudy.domain.model.facedetect.FaceDetectRepository;
import com.casestudy.domain.model.facedetect.FaceDetectRequest;
import com.casestudy.domain.model.facedetect.FaceDetectResponse;
import com.casestudy.infrastructure.datasource.Temporary;
import com.casestudy.infrastructure.datasource.Workingplace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import static org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
class FaceDetectDataSource implements FaceDetectRepository {

    Logger LOG = LoggerFactory.getLogger(FaceDetectDataSource.class);

    CascadeClassifier faceDetector;
    Boolean debugging;

    @Override
    public FaceDetectResponse detect(FaceDetectRequest request) {
        Workingplace.create();
        Temporary temporary = new Temporary(request.photo());
        temporary.write();

        try {
            FaceDetect faceDetect = new FaceDetect(temporary, faceDetector);
            faceDetect.detection();

            FaceDetectResponse response = faceDetect.response();
            if (response.isFailed())
                LOG.warn("faces not detected!");

            if (debugging)
                faceDetect.write();

            return response;
        } catch (Exception e) {
            LOG.error("face detect exception=" + e.getMessage());
            throw new FaceDetectException(e);
        } finally {
            temporary.remove();
        }
    }

    FaceDetectDataSource(CascadeClassifier faceDetector, @Value("${haarcascades.debugging:false}") Boolean debugging ) {
        this.faceDetector = faceDetector;
        this.debugging = debugging;
    }
}