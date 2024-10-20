package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name ="program")
public class Program extends  BaseEntity{
    int dayOfWeek;
    LocalTime hour;
    @ManyToOne
    Teacher teacher;
    @ManyToOne
    Grade grade;
    String classroom;
}
