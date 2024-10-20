package com.example.classbook1.web.view.models;

import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.TeacherDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ProgramViewModel {
    int dayOfWeek;
    LocalTime hour;
    TeacherDTO teacher;
    GradeDTO grade;
    String classroom;

}
