package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.Director;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolViewModel {
    private long id;
    private String name;
    private String address;
    //private Director director;
}
