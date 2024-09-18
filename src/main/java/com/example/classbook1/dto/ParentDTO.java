package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ParentDTO {
    private long id;
    private String name;
    private String lastName;
    private Set<Student> children;
}
