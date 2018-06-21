package com.casestudy.application.service

import com.casestudy.application.service.facedetect.FaceDetectService
import com.casestudy.domain.model.facedetect.FaceDetectRequest
import com.casestudy.domain.model.facedetect.FaceDetectResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@SpringBootTest(properties= "haarcascades.debugging=true")
class PhotoCaseStudySpec extends Specification {

    @Autowired
    FaceDetectService faceDetectService

    def "顔認識に成功する"() {
        setup:
        File file = new File(this.getClass().getResource("/data/dummy/face/test2.jpg").getFile())
        BufferedImage bufferedImage = ImageIO.read(file)

        // OpenCV使って画像の顔認証
        FaceDetectRequest request = new FaceDetectRequest(bufferedImage)
        FaceDetectResponse response = faceDetectService.detect(request)

        expect:
        response.name() == 'success'
    }

    def "顔認識に失敗する"() {
        setup:
        File file = new File(this.getClass().getResource("/data/dummy/notface/test4.jpg").getFile())
        BufferedImage bufferedImage = ImageIO.read(file)

        // OpenCV使って画像の顔認証
        FaceDetectRequest request = new FaceDetectRequest(bufferedImage)
        FaceDetectResponse response = faceDetectService.detect(request)

        expect:
        response.name() == 'failed'
    }
}