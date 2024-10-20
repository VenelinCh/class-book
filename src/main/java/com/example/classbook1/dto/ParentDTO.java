package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.data.entity.User;
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
    private Set<StudentDTO> children;
    private User user;
}
