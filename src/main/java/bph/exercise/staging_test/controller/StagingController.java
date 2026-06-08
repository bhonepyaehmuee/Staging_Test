package bph.exercise.staging_test.controller;

import bph.exercise.staging_test.service.StagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StagingController {
    @Autowired
    private StagingService stagingService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
