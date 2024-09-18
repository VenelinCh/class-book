package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParentViewModel {
    private long id;
    private String name;
    private String lastName;
    private List<Student> children;

}
