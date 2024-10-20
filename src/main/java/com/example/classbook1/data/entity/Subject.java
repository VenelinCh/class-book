package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="subject")
public class Subject extends BaseEntity{
    String subjectName;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjectsThatCanTeach")
    Set<Teacher> teachers;
}
