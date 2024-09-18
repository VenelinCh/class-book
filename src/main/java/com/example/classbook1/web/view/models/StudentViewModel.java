package com.example.classbook1.web.view.models;

import com.example.classbook1.data.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentViewModel {
    private long id;
    private String name;
    private String lastName;
    private Grade grade;
    private boolean expelled;


    public String getGradeSignature(){

        String signature = this.grade.getNumber() + " " + this.grade.getLabel();
        return signature;
    }
}
