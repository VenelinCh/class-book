package com.example.classbook1.service;

import com.example.classbook1.data.entity.Student;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.UpdateStudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getStudents();
    Student create(CreateStudentDTO createStudentDTO);
    StudentDTO getStudent(long id);
    Student updateStudent(long id, UpdateStudentDTO updateStudentDTO);
    void deleteStudent(long id);
    StudentDTO getStudentByname(String name);
}
