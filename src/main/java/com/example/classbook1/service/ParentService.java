package com.example.classbook1.service;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.dto.ParentDTO;

import java.util.List;

public interface ParentService {
    List<ParentDTO> getParents();
    Parent create(ParentDTO parent);
    ParentDTO getParent(long id);
    Parent updateParent(long id, ParentDTO parent);
    void deleteParent(long id);
    ParentDTO getParentByname(String name);
    ParentDTO getParentBynameAndLastName(String name,String lastName);
}