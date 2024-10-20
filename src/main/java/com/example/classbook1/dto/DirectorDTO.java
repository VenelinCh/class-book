package com.example.classbook1.dto;

import com.example.classbook1.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DirectorDTO {
    private long id;
    private String name;
    private String lastName;
    private SchoolDTO school;
    private LocalDate directorSince;
}
