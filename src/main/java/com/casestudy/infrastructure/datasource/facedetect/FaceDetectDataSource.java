package com.casestudy.infrastructure.datasource.facedetect;

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

    CascadeClassifier faceDetector;
    Boolean debugging;

    Logger LOG = LoggerFactory.getLogger(FaceDetectDataSource.class);

    @Override
    public FaceDetectResponse detect(FaceDetectRequest request) {
        Workingplace.create();
        Temporary temporary = new Temporary(request.photo());
        temporary.write();

        FaceDetect faceDetect = new FaceDetect(temporary, faceDetector);
        faceDetect.detection();

        FaceDetectResponse response = faceDetect.response();
        if(response.isFailed())
            LOG.warn("faces not detected!");

        if(debugging)
            faceDetect.write();

        temporary.remove();
        return response;
    }

    FaceDetectDataSource(CascadeClassifier faceDetector, @Value("${haarcascades.debugging:false}") Boolean debugging ) {
        this.faceDetector = faceDetector;
        this.debugging = debugging;
    }
}