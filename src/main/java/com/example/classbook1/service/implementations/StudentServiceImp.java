package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Mark;
import com.example.classbook1.data.entity.Student;
import com.example.classbook1.data.repository.StudentRepository;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.UpdateStudentDTO;
import com.example.classbook1.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDTO> getStudents(){
        return studentRepository.findAll().stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }
    private StudentDTO convertToStudentDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }
    @Override
    public Student create(CreateStudentDTO createStudentDTO){
        return studentRepository.save(modelMapper.map(createStudentDTO, Student.class));
    }
    @Override
    public StudentDTO getStudent(long id){
        return modelMapper.map(studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id)), StudentDTO.class);
    }
    public Student updateStudent(long id, UpdateStudentDTO updateStudentDTO){
        Student student = modelMapper.map(updateStudentDTO, Student.class);
        student.setId(id);
        return studentRepository.save(student);
    }
    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }
    public StudentDTO getStudentByname(String name){
        return modelMapper.map(studentRepository.findStudentByName(name), StudentDTO.class);
    }

    @Override
    public List<Mark> findMarksByStudentId(long id) {
        return studentRepository.findMarkByStudentId(id);
    }
}
