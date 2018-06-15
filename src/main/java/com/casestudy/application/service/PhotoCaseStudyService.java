package com.casestudy.application.service;

import org.springframework.stereotype.Service;

@Service
public class PhotoCaseStudyService {

    public void caseStudy() {
        System.out.println("photoCaseStudyService");
        // TODO OpenCV使って画像顔認証してみる
        // TODO OpenCV使ってトリミング(画像の顔認証から切り出す)してみる

        // TODO ClamAVで画像のウィルスチェックしてみる
    }
}