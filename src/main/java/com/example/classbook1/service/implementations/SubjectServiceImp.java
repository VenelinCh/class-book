package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Subject;
import com.example.classbook1.data.repository.SubjectRepository;
import com.example.classbook1.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SubjectServiceImp implements SubjectService {
    private final SubjectRepository subjectRepository;
    public List<Subject> getSubjects(){
        return subjectRepository.findAll().stream().collect(Collectors.toList());
    }
    public Subject getSubjectByName(String name){
        return subjectRepository.findSubjectBySubjectName(name);
    }
}
