package com.alten.opencvocr.repository;


import com.alten.opencvocr.entity.InputImageFaceRecon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputImageFaceReconRepository extends JpaRepository<InputImageFaceRecon, Long> {
    InputImageFaceRecon findByName(String name);
}

