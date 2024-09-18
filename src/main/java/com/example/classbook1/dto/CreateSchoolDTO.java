package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.data.entity.Grade;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateSchoolDTO {
    private long id;
    private String name;
    private String address;//da se napravi kato otdelen class
    private Director director;
    private boolean disabled;

}
