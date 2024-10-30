package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public Teacher findTeacherByName(String name);
    //List<Teacher> findTeacherByName(String name);
    public Teacher findTeacherByNameAndLastName(String name, String lastName);
}
