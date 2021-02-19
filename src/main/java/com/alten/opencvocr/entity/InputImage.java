package com.alten.opencvocr.entity;




import javax.persistence.*;

@Entity
public class InputImage {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Lob
    private String image;


    public InputImage(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public InputImage() {

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
