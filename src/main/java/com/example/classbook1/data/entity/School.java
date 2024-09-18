package com.example.classbook1.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "school")
public class School extends BaseEntity{
    private String name;
    private String address;//da se napravi kato otdelen class
    @OneToOne(mappedBy = "school")
    private Director director;
    private boolean disabled;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "school")
    private Set<Grade> grades;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "schools")
    private Set<Teacher> teachers;
}
