package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    public Director findDirectorByName (String name);
    public Director findDirectorByNameAndLastName (String name, String lastName);
    public Director findDirectorBySchool(School school);
}
