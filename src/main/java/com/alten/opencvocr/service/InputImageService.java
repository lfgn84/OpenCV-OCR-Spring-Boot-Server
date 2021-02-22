package com.alten.opencvocr.service;
import com.alten.opencvocr.entity.InputImageFaceRecon;
import com.alten.opencvocr.entity.InputImageOCR;
import com.alten.opencvocr.repository.InputImageFaceReconRepository;
import com.alten.opencvocr.repository.InputImageOCRRepository;
import com.alten.opencvocr.service.opencv.FaceDetection;
import com.alten.opencvocr.service.tasseract.OCR;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class InputImageService {
    Logger logger = LoggerFactory.getLogger(InputImageService.class);

    @Autowired
    InputImageOCRRepository inputImageOCRRepository;

    @Autowired
    InputImageFaceReconRepository inputImageFaceReconRepository;

    public String saveInputImage(MultipartFile image, String name) throws TesseractException, IOException {
        //     try{
        InputImageOCR inputImage = new InputImageOCR();
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String path = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name;
        if(imageName.contains("..")){
            System.out.println("Not a valid image name or file");
        }//else{
        inputImage.setImage(image.getBytes());
        File file = new File(path);
        image.transferTo(file);
        String detectedPath = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name +"."+detectImageFormat(file)+"";
        File file1 = new File(detectedPath);
        file.renameTo(file1);
        inputImage.setName(name);
        logger.info("File name : "+name+"");
        OCR ocr = new OCR(file1.getAbsolutePath(), name);
        inputImage.setDetectedText(ocr.getDetectedTxt());
        inputImageOCRRepository.save(inputImage);
        //  return inputImage.getDetectedText();
//        }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        return inputImage.getDetectedText();  // faster approach !

    }

    public byte[] saveImageForFaceRecon(MultipartFile image, String name) throws IOException {
        //
        //     try{
        InputImageFaceRecon inputImage = new InputImageFaceRecon();
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String path = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name;
        if(imageName.contains("..")){
            System.out.println("Not a valid image name or file");
        }//else{
        File file = new File(path);
        image.transferTo(file);
        String detectedPath = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/inFiles/"+ name +"."+detectImageFormat(file)+"";
        String targetPath = "C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/outFiles/"+ name +"."+detectImageFormat(file)+"";
        File file1 = new File(detectedPath);
        file.renameTo(file1);
        inputImage.setName(name);
        logger.info("File name : "+name+"");

        FaceDetection.detectFace(file1.getAbsolutePath(),targetPath);
        File processed = new File(targetPath);
        byte[] processedImageToBytes = FileUtils.readFileToByteArray(processed);
        inputImage.setImage(processedImageToBytes);
        inputImageFaceReconRepository.save(inputImage);

//                Path elCamino = Paths.get("C:/Users/seven/Desktop/REACT-NATIVE/springBootFileReceiver/outFiles/fromDB.jpg");
//                Files.write(elCamino,inputImage.getImage());    <---- get byte[] from entity/DB and convert to image file

        file.delete();
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        return processedImageToBytes;
    }


    public String detectImageFormat(File fileToDetect) throws IOException {
        // create an image input stream from the specified file
        ImageInputStream iis = ImageIO.createImageInputStream(fileToDetect);
        // get all currently registered readers that recognize the image format
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }
        // get the first reader
        ImageReader reader = iter.next();

        logger.info("Format: " + reader.getFormatName());
        // close stream
        iis.close();
        return reader.getFormatName();
    }

}

