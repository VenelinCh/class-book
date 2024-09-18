package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.repository.SchoolRepository;
import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.UpdateSchoolDTO;
import com.example.classbook1.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolServiceImp implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<SchoolDTO> getSchools(){
        return schoolRepository.findAll().stream()
                .map(this::convertToSchoolDTO)
                .collect(Collectors.toList());
    }
    private SchoolDTO convertToSchoolDTO(School school){
        return modelMapper.map(school, SchoolDTO.class);
    }
    @Override
    public School create(CreateSchoolDTO createSchoolDTO){
        return schoolRepository.save(modelMapper.map(createSchoolDTO, School.class));
    }
    @Override
    public SchoolDTO getSchool(long id){
        return modelMapper.map(schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school Id:" + id)), SchoolDTO.class);
    }
    public School updateSchool(long id, UpdateSchoolDTO updateSchoolDTO){
        School school = modelMapper.map(updateSchoolDTO, School.class);
        school.setId(id);
        return schoolRepository.save(school);
    }
    public School disableSchool(long id){

        SchoolDTO updateSchoolDTO = getSchool(id);
        updateSchoolDTO.setDisabled(true);

        School school = modelMapper.map(updateSchoolDTO, School.class);
        school.setId(id);
        return schoolRepository.save(school);
    }
    public void deleteSchool(long id){
        schoolRepository.deleteById(id);
    }
    public SchoolDTO getSchoolByname(String name){
        return modelMapper.map(schoolRepository.findSchoolByName(name), SchoolDTO.class);
    }
}
