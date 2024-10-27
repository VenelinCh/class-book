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
    public List<TeacherDTO> getTeachers();
    public Teacher create(CreateTeacherDTO createTeacherDTO);
    public TeacherDTO getTeacher(long id);
    public Teacher updateTeacher(long id, UpdateTeacherDTO updateTeacherDTO);
    public void deleteTeacher(long id);
    public TeacherDTO getTeacherByname(String name);
    public Set<SchoolDTO> getSchools();
    public Set<Subject> getSubjectsThatCanTeach(TeacherDTO teacher);
}
