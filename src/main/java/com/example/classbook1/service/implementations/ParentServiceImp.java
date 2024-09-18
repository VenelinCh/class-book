package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Parent;
import com.example.classbook1.data.repository.ParentRepository;
import com.example.classbook1.dto.ParentDTO;
import com.example.classbook1.service.ParentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParentServiceImp implements ParentService {
    private final ParentRepository ParentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ParentDTO> getParents(){
        return ParentRepository.findAll().stream()
                .map(this::convertToParentDTO)
                .collect(Collectors.toList());
    }
    private ParentDTO convertToParentDTO(Parent Parent){
        return modelMapper.map(Parent, ParentDTO.class);
    }
    @Override
    public Parent create(ParentDTO parentDTO){
        return ParentRepository.save(modelMapper.map(parentDTO, Parent.class));
    }
    @Override
    public ParentDTO getParent(long id){
        return modelMapper.map(ParentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Parent Id:" + id)), ParentDTO.class);
    }
    public Parent updateParent(long id, ParentDTO parentDTO){
        Parent Parent = modelMapper.map(parentDTO, Parent.class);
        Parent.setId(id);
        return ParentRepository.save(Parent);
    }
    public void deleteParent(long id){
        ParentRepository.deleteById(id);
    }
    public ParentDTO getParentByname(String name){
        return modelMapper.map(ParentRepository.findParentByName(name), ParentDTO.class);
    }
    public ParentDTO getParentBynameAndLastName(String name,String lastName){
        return modelMapper.map(ParentRepository.findParentByNameAndLastName(name, lastName), ParentDTO.class);
    }
}
