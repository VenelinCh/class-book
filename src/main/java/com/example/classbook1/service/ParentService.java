package com.example.classbook1.service;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.dto.ParentDTO;
import java.util.List;

public interface ParentService {
    public List<ParentDTO> getParents();
    public Parent create(ParentDTO parent);
    public ParentDTO getParent(long id);
    public Parent updateParent(long id, ParentDTO parent);
    public void deleteParent(long id);
    public ParentDTO getParentByName(String name);
    public ParentDTO getParentByNameAndLastName(String name,String lastName);
    public ParentDTO getParentByUserId(long id);
}
