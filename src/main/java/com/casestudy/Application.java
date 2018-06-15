package com.casestudy;

import com.casestudy.application.service.PhotoCaseStudyService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    PhotoCaseStudyService photoCaseStudyService;

    @Override
    public void run(String... args) throws Exception {
        photoCaseStudyService.caseStudy();
    }

    Application(PhotoCaseStudyService photoCaseStudyService) {
        this.photoCaseStudyService = photoCaseStudyService;
    }
}