package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Director;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolDTO {
    private long id;
    private String name;
    private String address;//da se napravi kato otdelen class
    private Director director;
    private boolean disabled;
}
