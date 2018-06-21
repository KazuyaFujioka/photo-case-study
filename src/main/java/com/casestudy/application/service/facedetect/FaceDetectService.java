package com.casestudy.application.service.facedetect;

import com.casestudy.domain.model.facedetect.FaceDetectRepository;
import com.casestudy.domain.model.facedetect.FaceDetectRequest;
import com.casestudy.domain.model.facedetect.FaceDetectResponse;

import org.springframework.stereotype.Service;

@Service
public class FaceDetectService {

    FaceDetectRepository faceDetectRepository;

    public FaceDetectResponse detect(FaceDetectRequest request) {
        return faceDetectRepository.detect(request);
    }

    FaceDetectService(FaceDetectRepository faceDetectRepository) {
        this.faceDetectRepository = faceDetectRepository;
    }
}