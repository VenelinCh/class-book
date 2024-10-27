package com.example.classbook1.web.view.controllers;


import com.example.classbook1.data.entity.Mark;
import com.example.classbook1.data.repository.StudentRepository;
import com.example.classbook1.dto.CreateStudentDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.dto.UpdateStudentDTO;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.StudentViewModel;
import com.fasterxml.jackson.core.JsonToken;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;
    GradeService gradeService;
    ModelMapper modelMapper;
    StudentRepository studentRepository;

    @GetMapping
    public String getStudents(Model model){
        final List<StudentViewModel> students = studentService.getStudents()
                .stream()
                .map(this::convertToViewModel)
                .collect(Collectors.toList());
        model.addAttribute("students", students);
        return "students/students";
    }
    @GetMapping("/a/create-student")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new StudentViewModel());
        List<GradeDTO> grades =  gradeService.getGrades();
        model.addAttribute("grades", grades);
        return "/students/create-student";
    }

    @PostMapping("/a/create")
    public String createStudent(@ModelAttribute("student") StudentViewModel student,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/students/create-student";
        }

        studentService.create(modelMapper.map(student, CreateStudentDTO.class));
        return "redirect:/students";
    }
    @GetMapping("/a/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/a/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model){
        StudentDTO student = studentService.getStudent(id);
        model.addAttribute("student", student);
        List<GradeDTO> grades = gradeService.getGrades();
        model.addAttribute("grades",grades);
        return "/students/update-student";

    }
    @PostMapping("/a/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute("student") StudentViewModel student,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/students/edit-student";
        }
        studentService.updateStudent(id, modelMapper.map(student, UpdateStudentDTO.class));
        return "redirect:/students";
    }
//    @GetMapping("/marks/{id}")
//    @ResponseBody
//    public String findMarks(@PathVariable("id")long id, Model model){
//
//        String strMark="";
//        try {
//            List<Mark> marks = studentService.findMarksByStudentId(id);
//            for (Mark m: marks){
//                String s = "" + m.getMark() +",";
//                strMark = strMark + ", " + m.getMark();
//            }
//        }catch (StackOverflowError e){//StackOverflow
//            System.out.println(e.getStackTrace());
//
//        }
//        return strMark;
//    }


    private  StudentViewModel convertToViewModel(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, StudentViewModel.class);
    }
}
