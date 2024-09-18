package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateStudentDTO {
    private long id;
    private String name;
    private String lastName;
    private Grade grade;
}