package com.alten.opencvocr.service;

import com.alten.opencvocr.entity.InputImage;
import com.alten.opencvocr.repository.InputImageRepository;
import com.alten.opencvocr.service.opencv.FaceDetection;
import com.alten.opencvocr.service.tasseract.OCR;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class InputImageService {
    Logger logger = LoggerFactory.getLogger(InputImageService.class);

    @Autowired
    InputImageRepository inputImageRepository;

    public void saveInputImage(MultipartFile image, String name) throws TesseractException, IOException {
        InputImage inputImage = new InputImage();
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String path = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name +".jpg";
        if(imageName.contains("..")){
            System.out.println("Not a valid image name or file");
        }try{

            inputImage.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            File file = new File(path);
            image.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        inputImage.setName(name);
        logger.info("File name : "+name+"");
        inputImageRepository.save(inputImage);

        OCR ocr = new OCR(path, name);
    }

    public void saveImageForFaceRecon(MultipartFile image, String name){
        InputImage inputImage = new InputImage();
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String path = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name +".jpg";
        String targetPath = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/outFiles/"+ name +".jpg";

        if(imageName.contains("..")){
            System.out.println("Not a valid image name or file");
        }try{

            inputImage.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            File file = new File(path);
            image.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        inputImage.setName(name);
        logger.info("File name : "+name+"");
        inputImageRepository.save(inputImage);

        FaceDetection.detectFace(path,targetPath);

    }
}
