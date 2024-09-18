package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradeViewModel {
    private long id;
    private int number;
    private String label;
    private School school;
}
