package com.alten.opencvocr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class InputImageFaceRecon {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Lob
    private byte[] image;



    public InputImageFaceRecon(Long id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public InputImageFaceRecon() {

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
}

