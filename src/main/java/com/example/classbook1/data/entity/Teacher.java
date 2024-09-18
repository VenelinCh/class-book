package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher extends Person{
    @ManyToMany//(fetch = FetchType.LAZY)
    private Set<School> schools;
}
