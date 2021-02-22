package com.alten.opencvocr.service.tasseract;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OCR {

    private String detectedTxt;

    public OCR (String path, String fileName) throws TesseractException {

        Logger logger = LoggerFactory.getLogger(OCR.class);
        try{
            String result = null;
            File image = new File(path);
            Tesseract tesseract = new Tesseract();
            tesseract.setTessVariable("user_defined_dpi", "300");
            tesseract.setLanguage("eng");
            BufferedImage bimg = ImageIO.read(image);
            int width = bimg.getWidth();
            int height = bimg.getHeight();

//            tesseract.setPageSegMode(1);
//            tesseract.setOcrEngineMode(1);
//            tesseract.setHocr(true);

            tesseract.setDatapath("src/main/resources/tessdata");
            result = tesseract.doOCR(image, new Rectangle(width, height));
            detectedTxt = result;
            logger.info("Image's width: "+width+" Image's height: "+height);
            //   System.out.println(result);
            logger.info(result);
        } catch (
                TesseractException | IOException e) {
            e.printStackTrace();
        }

    }
    public String getDetectedTxt(){
        return detectedTxt;
    }
}

