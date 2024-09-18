package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="parents")
public class Parent extends Person{
    @ManyToMany(mappedBy = "parents")
    private Set<Student> children;
}
