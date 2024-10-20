package com.example.classbook1.dto;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO {
    private long id;
    private String name;
    private String lastName;
    private Set<SchoolDTO> schools;
    private Set<Subject> subjectsThatCanTeach;
    //private SchoolDTO school;//remove
    private Subject subjectsStr;//remove
}
