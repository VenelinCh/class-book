package com.example.classbook1.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student extends Person{
    //private String surname;
    @ManyToMany//(mappedBy = "children")
    private Set<Parent> parents;
    @ManyToOne
    private Grade grade;
    private boolean expelled;
}
