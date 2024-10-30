package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    public Grade findGradeByNumber(int number);


}
