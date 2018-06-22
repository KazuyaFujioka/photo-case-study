package com.casestudy.application.service

import com.casestudy.application.service.adjustment.AdjustmentService
import com.casestudy.application.service.facedetect.FaceDetectService
import com.casestudy.domain.fundamental.Photo
import com.casestudy.domain.model.adjustment.AdjustmentRequest
import com.casestudy.domain.model.adjustment.AdjustmentResponse
import com.casestudy.domain.model.adjustment.AdjustmentSize
import com.casestudy.domain.model.facedetect.FaceDetectRequest
import com.casestudy.domain.model.facedetect.FaceDetectResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@SpringBootTest(properties= "haarcascades.debugging=false")
class PhotoCaseStudySpec extends Specification {

    @Autowired
    FaceDetectService faceDetectService

    @Autowired
    AdjustmentService adjustmentService

    def "顔認識に成功する"() {
        setup:
        File file = new File(this.getClass().getResource("/data/dummy/face/test2.jpg").getFile())
        BufferedImage bufferedImage = ImageIO.read(file)
        Photo photo = new Photo(bufferedImage)

        // OpenCV使って画像の顔認証
        FaceDetectRequest request = new FaceDetectRequest(photo)
        FaceDetectResponse response = faceDetectService.detect(request)

        expect:
        response.name() == 'success'
    }

    def "顔認識に失敗する"() {
        setup:
        File file = new File(this.getClass().getResource("/data/dummy/notface/test4.jpg").getFile())
        BufferedImage bufferedImage = ImageIO.read(file)
        Photo photo = new Photo(bufferedImage)

        // OpenCV使って画像の顔認証
        FaceDetectRequest request = new FaceDetectRequest(photo)
        FaceDetectResponse response = faceDetectService.detect(request)

        expect:
        response.name() == 'failed'
    }

    def "写真のサイズ調整(リサイズ)する"() {
        setup:
        File file = new File(this.getClass().getResource("/data/dummy/face/test.jpg").getFile())
        BufferedImage bufferedImage = ImageIO.read(file)
        Photo photo = new Photo(bufferedImage)

        AdjustmentSize size = new AdjustmentSize(354, 283)
        //AdjustmentSize size = new AdjustmentSize(1333, 2000)

        // OpenCV使って画像のサイズ調整
        AdjustmentRequest request = new AdjustmentRequest(photo, size)
        AdjustmentResponse response = adjustmentService.resize(request)

        expect:
        response.adjustmentImage() != null
    }
}