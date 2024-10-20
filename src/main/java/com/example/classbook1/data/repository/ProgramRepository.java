package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
