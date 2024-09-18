package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.dto.ParentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    ParentDTO findParentByName(String name);
    ParentDTO findParentByNameAndLastName(String name, String lastName);
    List<Parent> findParentByChildren(Student child);
}
