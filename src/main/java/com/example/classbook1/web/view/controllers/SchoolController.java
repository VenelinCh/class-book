package com.example.classbook1.web.view.controllers;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.entity.School;
import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.DirectorDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.service.DirectorService;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.SchoolViewModel;
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
@RequestMapping("/schools")
public class SchoolController {
    SchoolService schoolService;
//    GradeService gradeService;
//    DirectorService directorService;

    ModelMapper modelMapper;
    @GetMapping
    public String getSchools(Model model){
        final List<SchoolViewModel> schools = schoolService.getSchools()
                .stream()
                .map(this::convertToViewmodel)
                .collect(Collectors.toList());
        model.addAttribute("schools", schools);
        return "/schools/schools";
    }
    @GetMapping("/create-school")
    public String showCreateSchoolForm(Model model) {
        model.addAttribute("school", new SchoolViewModel());

        return "/schools/create-school";
    }

    //ne raboti
    @PostMapping("/create")
    public String createSchool(@ModelAttribute("school") SchoolViewModel school,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/schools/create-school";
        }
        schoolService.create(modelMapper.map(school, CreateSchoolDTO.class));
        return "redirect:/schools";
    }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {

        schoolService.deleteSchool(id);
        return "redirect:/schools";
    }
    @GetMapping("/disable/{id}")
    public String disableSchool(@PathVariable("id") long id) {
        schoolService.disableSchool(id);
        return "redirect:/schools";
    }
    private SchoolViewModel convertToViewmodel(SchoolDTO schoolDTO){
        return modelMapper.map(schoolDTO, SchoolViewModel.class);
    }
}
