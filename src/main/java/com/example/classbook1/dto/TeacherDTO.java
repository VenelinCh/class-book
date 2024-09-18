package com.example.classbook1.dto;

import com.example.classbook1.data.entity.School;
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
}
