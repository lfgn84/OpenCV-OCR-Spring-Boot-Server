package com.alten.opencvocr.service.tasseract;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;

public class OCR {

    private String path;

    public OCR (String path, String fileName) throws TesseractException {
        Logger logger = LoggerFactory.getLogger(OCR.class);
        try{
            String result = null;
            File image = new File(path);
            Tesseract tesseract = new Tesseract();
            tesseract.setTessVariable("user_defined_dpi", "300");
            tesseract.setLanguage("eng");
            int width = 0;
            int height = 0;
            if(fileName.equals("maradona")){
                width = 309;
                height = 394;
            }
            if(fileName.equals("letters")){
                width = 365;
                height = 576;
            }
//            tesseract.setPageSegMode(1);
//            tesseract.setOcrEngineMode(1);
//            tesseract.setHocr(true);

            tesseract.setDatapath("src/main/resources/tessdata");
            result = tesseract.doOCR(image, new Rectangle(width, height));
         //   System.out.println(result);
            logger.info(result);
        } catch (
                TesseractException e) {
            e.printStackTrace();
        }

    }
}
