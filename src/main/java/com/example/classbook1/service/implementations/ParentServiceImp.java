package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.data.repository.ParentRepository;
import com.example.classbook1.dto.ParentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.service.ParentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParentServiceImp implements ParentService {
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ParentDTO> getParents(){
        return parentRepository.findAll().stream()
                .map(this::convertToParentDTO)
                .collect(Collectors.toList());
    }
    private ParentDTO convertToParentDTO(Parent Parent){
        return modelMapper.map(Parent, ParentDTO.class);
    }
    @Override
    public Parent create(ParentDTO parentDTO){
        return parentRepository.save(modelMapper.map(parentDTO, Parent.class));
    }
    @Override
    public ParentDTO getParent(long id){
        return modelMapper.map(parentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Parent Id:" + id)), ParentDTO.class);
    }
    public Parent updateParent(long id, ParentDTO  parentDTO){
        Parent parent = modelMapper.map(parentDTO, Parent.class);//revert it later
//        Set<Student> children = new HashSet<>();
//        for(StudentDTO ch: parentDTO.getChildren()){
//            children.add(modelMapper.map(ch, Student.class));
//        }
//        parent.setChildren(children);
        parent.setId(id);
        return parentRepository.save(parent);
    }
    public void deleteParent(long id){
        parentRepository.deleteById(id);
    }
    public ParentDTO getParentByName(String name){
        return modelMapper.map(parentRepository.findParentByName(name), ParentDTO.class);
    }
    public ParentDTO getParentByNameAndLastName(String name,String lastName){
        return modelMapper.map(parentRepository.findParentByNameAndLastName(name, lastName), ParentDTO.class);
    }
    @Override
    public ParentDTO getParentByUserId(long id){
        Parent p = new Parent();
        try{
            p =parentRepository.findParentByUserId(id);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return modelMapper.map(p, ParentDTO.class);
    }
}
