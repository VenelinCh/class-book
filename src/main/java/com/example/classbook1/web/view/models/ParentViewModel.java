package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.Student;
import com.example.classbook1.dto.StudentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ParentViewModel {
    private long id;
    private String name;
    private String lastName;
    private Set<StudentDTO> children;

}
