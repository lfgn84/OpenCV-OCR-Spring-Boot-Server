package com.alten.opencvocr.entity;





import javax.persistence.*;

@Entity
public class InputImageOCR {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Lob
    private byte[] image;
    @Lob
    private String detectedText;


    public InputImageOCR(Long id, String name, byte[] image, String detectedText) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.detectedText = detectedText;
    }

    public InputImageOCR(Long id, String name, byte[] image){
        this.id = id;
        this.name = name;
        this. image = image;
    }

    public InputImageOCR() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDetectedText() {
        return detectedText;
    }

    public void setDetectedText(String detectedText) {
        this.detectedText = detectedText;
    }
}

