package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DirectorViewModel {
    private long id;
    private String name;
    private String lastName;
    private School school;
    private LocalDate directorSince;
    public String getSchoolName(){
        String schoolNane ;
        try {
            schoolNane = this.school.getName();
            return schoolNane;

        }catch(NullPointerException e){
            schoolNane = "";
            e.getStackTrace();
        }
        return schoolNane;
    }
}
