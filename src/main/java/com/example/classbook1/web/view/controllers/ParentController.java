package com.example.classbook1.web.view.controllers;


import com.example.classbook1.data.repository.ParentRepository;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.ParentDTO;
import com.example.classbook1.dto.StudentDTO;
import com.example.classbook1.service.GradeService;
import com.example.classbook1.service.ParentService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.ParentViewModel;
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
@RequestMapping("/parents")
public class ParentController {
    ParentService parentService;
    StudentService studentService;
    ModelMapper modelMapper;
    ParentRepository parentRepository;
    @GetMapping
    public String getParents(Model model){
        final List<ParentViewModel> parents = parentService.getParents()
                .stream()
                .map(this::convertToViewModel)
                .collect(Collectors.toList());
        model.addAttribute("parents", parents);
        return "parents/parents";
    }
    @GetMapping("/create-parent")
    public String showCreateParentForm(Model model) {
        model.addAttribute("parent", new ParentViewModel());
        List<StudentDTO> children = studentService.getStudents();
        model.addAttribute("children", children);
        return "/parents/create-parent";
    }

    @PostMapping("/create")
    public String createParent(@ModelAttribute("parent") ParentViewModel parent,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/parents/create-parent";
        }
        parentService.create(modelMapper.map(parent, ParentDTO.class));
        return "redirect:/parents";
    }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        parentService.deleteParent(id);
        return "redirect:/parents";
    }
    @GetMapping("/edit/{id}")
    public String editParent(@PathVariable("id") long id, Model model){
        ParentDTO parent = parentService.getParent(id);
        model.addAttribute("parent", parent);
        List<StudentDTO> children = studentService.getStudents();
        model.addAttribute("children",children);
        return "/parents/update-parent";

    }
    @PostMapping("/update/{id}")
    public String updateParent(@PathVariable("id") long id, @ModelAttribute("parent") ParentViewModel parent,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/parents/edit-parent";
        }
        parentService.updateParent(id, modelMapper.map(parent, ParentDTO.class));
        return "redirect:/parents";
    }
    @GetMapping("/parent/{id}")
    public String viewParent(@PathVariable("id") long id, Model model) {
        ParentDTO parent=parentService.getParent(id);
        model.addAttribute("parent", parent);
        List<StudentDTO> children = studentService.getStudents();
        model.addAttribute("children",children);
        return "/parents/parent";
    }

    private  ParentViewModel convertToViewModel(ParentDTO parentDTO){
        return modelMapper.map(parentDTO, ParentViewModel.class);
    }
}
