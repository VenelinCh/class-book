package com.example.classbook1.data.repository;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.dto.ParentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    ParentDTO findParentByName(String name);
    ParentDTO findParentByNameAndLastName(String name, String lastName);
    //List<Parent> findParentByChildren(Student child);

    @Query("SELECT p FROM Parent p JOIN p.user u WHERE u.id = :id")
    Parent findParentByUserId(long id);
}
