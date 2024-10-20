package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findSubjectBySubjectName(String name);
}
