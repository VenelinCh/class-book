package com.example.classbook1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTeacherDTO {
    private long id;
    private String name;
    private String lastName;
}
