package com.example.classbook1.web.view.controllers;


import com.example.classbook1.data.repository.StudentRepository;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.UpdateStudentDTO;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.StudentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;
    GradeService gradeService;
    ModelMapper modelMapper;
    StudentRepository studentRepository;
    /////////////////

    //////////
    @GetMapping
    public String getStudents(Model model){
        final List<StudentViewModel> students = studentService.getStudents()
                .stream()
                .map(this::convertToViewModel)
                .collect(Collectors.toList());
        model.addAttribute("students", students);
//        final List<GradeDTO> grades = gradeService.getGrades();
//        model.addAttribute("grades", grades);
        return "students/students";
    }
    @GetMapping("/create-student")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new StudentViewModel());
        List<GradeDTO> grades =  gradeService.getGrades();
        model.addAttribute("grades", grades);
        return "/students/create-student";
    }

    //ne raboti
    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") StudentViewModel student,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/students/create-student";
        }
        ////////////////
        //System.out.println("======================================" + student.getGrade() + "=============================");
        //student.setGrade(gade);
        ///////////////////////
        studentService.create(modelMapper.map(student, CreateStudentDTO.class));
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model){
        StudentDTO student = studentService.getStudent(id);
        model.addAttribute("student", student);
        List<GradeDTO> grades = gradeService.getGrades();
        model.addAttribute("grades",grades);
        return "/students/update-student";

    }
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute("student") StudentViewModel student,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/students/edit-student";
        }
        studentService.updateStudent(id, modelMapper.map(student, UpdateStudentDTO.class));
        return "redirect:/students";
    }

    private  StudentViewModel convertToViewModel(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, StudentViewModel.class);
    }
}
