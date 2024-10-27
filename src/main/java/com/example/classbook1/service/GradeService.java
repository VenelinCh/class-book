package com.example.classbook1.service;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.dto.CreateGradeDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.UpdateGradeDTO;

import java.util.List;

public interface GradeService {
    public List<GradeDTO> getGrades();
    public Grade create(CreateGradeDTO createGradeDTO);
    public GradeDTO getGrade(long id);
    public Grade updateGrade(long id, UpdateGradeDTO updateGradeDTO);
    public void deleteGrade(long id);
    public GradeDTO getGradeByNumber(int number);
}
