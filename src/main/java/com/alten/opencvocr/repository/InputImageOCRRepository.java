package com.alten.opencvocr.repository;

import com.alten.opencvocr.entity.InputImageOCR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputImageOCRRepository extends JpaRepository<InputImageOCR, Long> {
    InputImageOCR findByName(String name);
}

