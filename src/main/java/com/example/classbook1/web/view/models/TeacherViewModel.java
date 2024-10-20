package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.Subject;
import com.example.classbook1.dto.SchoolDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeacherViewModel {
    private long id;
    private String name;
    private String lastName;
    private Set<SchoolDTO> schools;
    private Set<Subject> subjectsThatCanTeach;
    //private SchoolDTO school;
    private String subjectsStr;

}
