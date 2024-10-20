package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTeacherDTO {
    private long id;
    private String name;
    private String lastName;
    Set<SchoolDTO> schools;
    Set<Subject> subjectsThatCanTeach;
}
