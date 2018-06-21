package com.casestudy.infrastructure.configuration;

import static org.bytedeco.javacpp.opencv_objdetect.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
class ComputerVisionConfiguration {

    // OpenCVで用意している分類器を読み込む
    @Value("${haarcascades}")
    File haarcascades;

    @Bean
    CascadeClassifier faceDetector() {
        CascadeClassifier faceDetector = new CascadeClassifier(haarcascades.getPath());
        return faceDetector;
    }
}