package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
    School findSchoolByName(String name);
}
