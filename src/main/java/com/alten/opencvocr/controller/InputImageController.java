package com.alten.opencvocr.controller;
import com.alten.opencvocr.repository.InputImageFaceReconRepository;
import com.alten.opencvocr.repository.InputImageOCRRepository;
import com.alten.opencvocr.service.InputImageService;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Autowired
    InputImageOCRRepository inputImageOCRRepository;

    @Autowired
    InputImageFaceReconRepository inputImageFaceReconRepository;

    @PostMapping("/inputImage")
    public String loadImage(@RequestParam("image")MultipartFile image) throws TesseractException, IOException {
        String name = RandomStringUtils.randomAlphanumeric(10);
        return  inputImageService.saveInputImage(image, name); // <---- faster approach 4/5 sec



    }

    @PostMapping("/faceRecon")
    public byte[] loadImageForFaceRecon(@RequestParam("image")MultipartFile image) throws IOException {
        String name = RandomStringUtils.randomAlphanumeric(10);
        return inputImageService.saveImageForFaceRecon(image, name);  // <---- faster approach 3 sec
    }
}

