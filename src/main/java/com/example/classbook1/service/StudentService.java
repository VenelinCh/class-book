package com.example.classbook1.service;

import com.example.classbook1.data.entity.Mark;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.UpdateStudentDTO;

import java.util.List;

public interface StudentService {
    public List<StudentDTO> getStudents();
    public Student create(CreateStudentDTO createStudentDTO);
    public StudentDTO getStudent(long id);
    public Student updateStudent(long id, UpdateStudentDTO updateStudentDTO);
    public void deleteStudent(long id);
    public StudentDTO getStudentByname(String name);
    public List<Mark> findMarksByStudentId(long id);
}
