package com.alten.opencvocr.controller;

import com.alten.opencvocr.service.InputImageService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class InputImageController {

    @Autowired
    InputImageService inputImageService;

    @PostMapping("/inputImage")
    public void loadImage(@RequestParam("image")MultipartFile image, String name) throws TesseractException, IOException {
        inputImageService.saveInputImage(image, name);
    }

    @PostMapping("/faceRecon")
    public void loadImageForFaceRecon(@RequestParam("image")MultipartFile image, String name){
        inputImageService.saveImageForFaceRecon(image, name);
    }
}
