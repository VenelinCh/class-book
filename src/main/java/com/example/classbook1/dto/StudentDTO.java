package com.example.classbook1.dto;

import com.example.classbook1.data.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private long id;
    private String name;
    private String lastName;
    private Grade grade;
    private boolean expelled;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", expelled=" + expelled +
                '}';
    }
}
