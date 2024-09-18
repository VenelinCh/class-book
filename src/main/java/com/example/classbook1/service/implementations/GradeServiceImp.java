package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.repository.GradeRepository;
import com.example.classbook1.dto.CreateGradeDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.UpdateGradeDTO;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.GradeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GradeServiceImp implements GradeService {
    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<GradeDTO> getGrades(){
        return gradeRepository.findAll().stream()
                .map(this::convertToGradeDTO)
                .collect(Collectors.toList());
    }
    private GradeDTO convertToGradeDTO(Grade grade){
        return modelMapper.map(grade, GradeDTO.class);
    }
    @Override
    public Grade create(CreateGradeDTO createGradeDTO){
        return gradeRepository.save(modelMapper.map(createGradeDTO, Grade.class));
    }
    @Override
    public GradeDTO getGrade(long id){
        return modelMapper.map(gradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid grade Id:" + id)), GradeDTO.class);
    }
    public Grade updateGrade(long id, UpdateGradeDTO updateGradeDTO){
        Grade grade = modelMapper.map(updateGradeDTO, Grade.class);
        grade.setId(id);
        return gradeRepository.save(grade);
    }
    public void deleteGrade(long id){
        gradeRepository.deleteById(id);
    }
    public GradeDTO getGradeByNumber(int number){
        return modelMapper.map(gradeRepository.findGradeByNumber(number), GradeDTO.class);
    }
}