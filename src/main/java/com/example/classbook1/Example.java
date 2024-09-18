package com.example.classbook1;

import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.StudentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor

public class Example {
    StudentService studentService;
    ModelMapper modelMapper;

    public void createStudent(StudentViewModel student){
        studentService.create(modelMapper.map(student, CreateStudentDTO.class));
    }
}
