package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Mark;
import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByName(String name);
    @Query("SELECT m FROM Mark m JOIN m.student s WHERE s.id = :id")
    List<Mark> findMarkByStudentId(long id);
}
