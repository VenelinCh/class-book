package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name ="grade")
public class Grade extends BaseEntity{
    private int number;
    private String label;
    @ManyToOne
    private School school;
    @OneToMany
    private List<Student> students;

    public String getSignature(){
        return "signature";
    }
    }
