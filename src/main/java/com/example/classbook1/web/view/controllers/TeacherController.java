package com.example.classbook1.web.view.controllers;

import com.example.classbook1.dto.*;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.service.TeacherService;
import com.example.classbook1.web.view.models.SchoolViewModel;
import com.example.classbook1.web.view.models.StudentViewModel;
import com.example.classbook1.web.view.models.TeacherViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    TeacherService teacherService;
    ModelMapper modelMapper;
    SchoolService schoolService;
    @GetMapping
    public String getTeachers(Model model){
        final List<TeacherViewModel> teachers = teacherService.getTeachers()
                .stream()
                .map(this::convertToViewmodel)
                .collect(Collectors.toList());
        model.addAttribute("teachers", teachers);
        return "/teachers/teachers";
    }
    @GetMapping("/create-teacher")
    public String showCreateSchoolForm(Model model) {
        model.addAttribute("teacher", new TeacherViewModel());
        model.addAttribute("schools", new SchoolViewModel());

        return "/teachers/create-teacher";
    }

    //ne raboti
    @PostMapping("/create")
    public String createTeacher(@ModelAttribute("teacher") TeacherViewModel teacher,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/teachers/create-teacher";
        }
        teacherService.create(modelMapper.map(teacher, CreateTeacherDTO.class));
        return "redirect:/teachers";
    }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable("id") long id, Model model){
        TeacherDTO teacher = teacherService.getTeacher(id);
        model.addAttribute("teacher", teacher);
        Set<SchoolDTO> schools = teacherService.getSchools(teacher);
        model.addAttribute("schools",schools);
        //List<GradeDTO> grades = gradeService.getGrades();
        //model.addAttribute("grades",grades);
        return "/teachers/update-teacher";

    }
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") long id, @ModelAttribute("teacher") TeacherViewModel teacher,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/teachers/edit-teacher";
        }
        Set<SchoolDTO> schools = teacherService.getTeacher(id).getSchools();
        teacher.setSchools(schools);
        teacherService.updateTeacher(id, modelMapper.map(teacher, UpdateTeacherDTO.class));
        return "redirect:/teachers";
    }
    private TeacherViewModel convertToViewmodel(TeacherDTO teacherDTO){
        return modelMapper.map(teacherDTO, TeacherViewModel.class);
    }

    @GetMapping("/test/{id}")
    private String test(@PathVariable("id") long id, @ModelAttribute("teacher") TeacherDTO teacher, BindingResult bindingResult){
        Set<SchoolDTO> schools = teacherService.getTeacher(id).getSchools();
        System.out.println(
                "======================="
        );
        for(SchoolDTO school : schools){
            System.out.println(school.getName());
        }
        return "redirect:/teachers";
    }
}
