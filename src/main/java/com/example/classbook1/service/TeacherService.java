package com.example.classbook1.service;

import com.example.classbook1.data.entity.Subject;
import com.example.classbook1.data.entity.Teacher;
import com.example.classbook1.dto.CreateTeacherDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.TeacherDTO;
import com.example.classbook1.dto.UpdateTeacherDTO;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    List<TeacherDTO> getTeachers();
    Teacher create(CreateTeacherDTO createTeacherDTO);
    TeacherDTO getTeacher(long id);
    Teacher updateTeacher(long id, UpdateTeacherDTO updateTeacherDTO);
    void deleteTeacher(long id);
    TeacherDTO getTeacherByname(String name);
    Set<SchoolDTO> getSchools();
    Set<Subject> getSubjectsThatCanTeach(TeacherDTO teacher);
}
