package com.example.classbook1.service;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.UpdateSchoolDTO;

import java.util.List;

public interface SchoolService {
    public List<SchoolDTO> getSchools();
    public School create(CreateSchoolDTO createSchoolDTO);
    public SchoolDTO getSchool(long id);
    public School updateSchool(long id, UpdateSchoolDTO updateSchoolDTO);
    public void deleteSchool(long id);
    public School disableSchool(long id);
    public SchoolDTO getSchoolByname(String name);



}
