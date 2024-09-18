package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByName(String name);
}
