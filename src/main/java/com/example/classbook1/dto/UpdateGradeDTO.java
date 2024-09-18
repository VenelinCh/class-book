package com.example.classbook1.dto;

import com.example.classbook1.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateGradeDTO {
    private long id;
    private int number;
    private String label;
    private School school;
}