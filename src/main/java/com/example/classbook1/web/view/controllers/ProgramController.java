package com.example.classbook1.web.view.controllers;


import com.example.classbook1.data.entity.Program;
import com.example.classbook1.data.entity.User;
import com.example.classbook1.data.repository.ProgramRepository;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.TeacherDTO;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.ProgramService;
import com.example.classbook1.service.TeacherService;
import com.example.classbook1.web.view.models.ProgramViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/programs")
public class ProgramController {
    ProgramService programService;
    GradeService gradeService;
    TeacherService teacherService;
    ModelMapper modelMapper;
    ProgramRepository programrepository;

//    @GetMapping
//    public String getPrograms(Model model){
//        final List<Program> programs = programService.getPrograms().stream().collect(Collectors.toList());
//        model.addAttribute("programs", programs);/////
//        return "programs/programs";
//    }
    @GetMapping
    @ResponseBody
    public String getPrograms(@AuthenticationPrincipal User user){
    final List<Program> programs = programService.getPrograms().stream().collect(Collectors.toList());
    //model.addAttribute("programs", programs);/////
    return "Hello, " + user.getUsername();
}


    @GetMapping("/create-program")
    public String showCreateProgramForm(Model model) {
        model.addAttribute("program", new ProgramViewModel());
        List<GradeDTO> grades = gradeService.getGrades();
        model.addAttribute("grades", grades);
        List<TeacherDTO> teachers = teacherService.getTeachers();
        model.addAttribute("teachers", teachers);
        return "/programs/create-program";
    }

    //ne raboti
    @PostMapping("/create")
    public String createProgram(@ModelAttribute("program") ProgramViewModel program,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/programs/create-program";
        }
        programService.create(modelMapper.map(program, Program.class));
        return "redirect:/programs";
    }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        programService.deleteProgram(id);
        return "redirect:/programs";
    }
    @GetMapping("/edit/{id}")
    public String editProgram(@PathVariable("id") long id, Model model){
        Program program = programService.getProgram(id);
        model.addAttribute("program", program);
        List<TeacherDTO> teachers = teacherService.getTeachers();
        model.addAttribute("teachers",teachers);
        List<GradeDTO> grades = gradeService.getGrades();
        model.addAttribute("grades",grades);
        return "/programs/update-program";

    }
    @PostMapping("/update/{id}")
    public String updateProgram(@PathVariable("id") long id, @ModelAttribute("program") ProgramViewModel program,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/programs/edit-program";
        }
        programService.updateProgram(id, modelMapper.map(program, Program.class));
        return "redirect:/programs";
    }

    private  ProgramViewModel convertToViewModel(Program program){
        return modelMapper.map(program, ProgramViewModel.class);
    }
}
