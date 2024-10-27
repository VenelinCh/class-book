package com.example.classbook1.service;

import com.example.classbook1.data.entity.Subject;

import java.util.List;

public interface SubjectService {
    public List<Subject> getSubjects();
    public Subject getSubjectByName(String  name);
}
