package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="mark")
public class Mark extends BaseEntity{
    private int mark;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Subject subject;

    public String getTeacherStr(){
        return  this.getTeacher().getName() + " " + this.getTeacher().getLastName();
    }
    public String getSubjectStr(){
        return  this.getSubject().subjectName;
    }
}
