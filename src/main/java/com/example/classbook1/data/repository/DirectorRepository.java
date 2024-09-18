package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findDirectorByName (String name);
    Director findDirectorByNameAndLastName (String name, String lastName);
    Director findDirectorBySchool(School school);
}
