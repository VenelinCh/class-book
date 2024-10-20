package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Subject;
import com.example.classbook1.data.entity.Teacher;
import com.example.classbook1.data.repository.TeacherRepository;
import com.example.classbook1.dto.CreateTeacherDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.TeacherDTO;
import com.example.classbook1.dto.UpdateTeacherDTO;
import com.example.classbook1.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImp implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<TeacherDTO> getTeachers(){
        return teacherRepository.findAll().stream()
                .map(this::convertToTeacherDTO)
                .collect(Collectors.toList());
    }
    private TeacherDTO convertToTeacherDTO(Teacher teacher){
        return modelMapper.map(teacher, TeacherDTO.class);
    }
    @Override
    public Teacher create(CreateTeacherDTO createTeacherDTO){
        return teacherRepository.save(modelMapper.map(createTeacherDTO, Teacher.class));
    }
    @Override
    public TeacherDTO getTeacher(long id){
        return modelMapper.map(teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id)), TeacherDTO.class);
    }
    public Teacher updateTeacher(long id, UpdateTeacherDTO updateTeacherDTO){
        Teacher teacher = modelMapper.map(updateTeacherDTO, Teacher.class);
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }
    public void deleteTeacher(long id){
        teacherRepository.deleteById(id);
    }
    public TeacherDTO getTeacherByname(String name){
        return modelMapper.map(teacherRepository.findTeacherByName(name), TeacherDTO.class);
    }

    public Set<SchoolDTO> getSchools(){
        //TeacherDTO teacher = getTeacher(id);
        return this.getSchools();
    }
    public Set<Subject> getSubjectsThatCanTeach(TeacherDTO teacherDTO){
        return teacherDTO.getSubjectsThatCanTeach();
    }
}
