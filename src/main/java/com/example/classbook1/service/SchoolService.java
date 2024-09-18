package com.example.classbook1.service;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.UpdateSchoolDTO;

import java.util.List;

public interface SchoolService {
    List<SchoolDTO> getSchools();
    School create(CreateSchoolDTO createSchoolDTO);
    SchoolDTO getSchool(long id);
    School updateSchool(long id, UpdateSchoolDTO updateSchoolDTO);
    void deleteSchool(long id);
    School disableSchool(long id);
    SchoolDTO getSchoolByname(String name);



}
