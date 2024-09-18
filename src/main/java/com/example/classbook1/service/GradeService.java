package com.example.classbook1.service;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.dto.CreateGradeDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.UpdateGradeDTO;

import java.util.List;

public interface GradeService {
    List<GradeDTO> getGrades();
    Grade create(CreateGradeDTO createGradeDTO);
    GradeDTO getGrade(long id);
    Grade updateGrade(long id, UpdateGradeDTO updateGradeDTO);
    void deleteGrade(long id);
    GradeDTO getGradeByNumber(int number);
}
