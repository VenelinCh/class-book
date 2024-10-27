package com.example.classbook1.web.view.controllers;


import com.example.classbook1.data.entity.Mark;
import com.example.classbook1.data.entity.User;
import com.example.classbook1.data.repository.ParentRepository;
import com.example.classbook1.dto.*;
import com.example.classbook1.service.ParentService;
import com.example.classbook1.service.StudentService;
import com.example.classbook1.web.view.models.ParentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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
    @GetMapping("/a/create-parent")
    public String showCreateParentForm(Model model) {
        model.addAttribute("parent", new ParentViewModel());
        List<StudentDTO> children = studentService.getStudents();
        model.addAttribute("children", children);
        return "/parents/create-parent";
    }

    @PostMapping("/a/create")//admin
    public String createParent(@ModelAttribute("parent") ParentViewModel parent,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/parents/create-parent";
        }
        parentService.create(modelMapper.map(parent, ParentDTO.class));
        return "redirect:/parents";
    }
    @GetMapping("/a/delete/{id}")//can be seen by admin
    public String deleteTeacher(@PathVariable("id") long id) {
        parentService.deleteParent(id);
        return "redirect:/parents";
    }
    @GetMapping("/a/edit/{id}")//can be seen by admin
    public String editParent(@PathVariable("id") long id, Model model){
        ParentDTO parent = parentService.getParent(id);
        model.addAttribute("parent", parent);
        List<StudentDTO> children = studentService.getStudents();
        model.addAttribute("children",children);
        return "/parents/update-parent";

    }

@GetMapping("/my-profile")
public String myProfile(@AuthenticationPrincipal User user, Model model){
    try {
        long id = user.getId();
        ParentDTO parent = parentService.getParentByUserId(id);
        if(user.getId()==parent.getUser().getId()){
            model.addAttribute("parent", parent);
            Set<StudentDTO> children = parent.getChildren();
            model.addAttribute("children",children);

        }
    }catch(Exception e){
        System.out.println(e.getStackTrace());
    }
    return "/parents/my-profile";
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
@GetMapping("/marks/{id}")//can be seen by parent, admin
//    @ResponseBody
    public String findMarks(@PathVariable("id")long id,@AuthenticationPrincipal User user, Model model){
        try {
            long idParent = user.getId();
            ParentDTO parent = parentService.getParentByUserId(idParent);
            if(user.getId()!=parent.getUser().getId()){
                return "redirect:/parents";
            }
            StudentDTO student = new StudentDTO();
            Set<StudentDTO> students = parent.getChildren();
            for(StudentDTO s:students){
                if(s.getId() == id){
                    student= studentService.getStudent(id);
                    model.addAttribute("student",student);
                    List<Mark> marks = studentService.findMarksByStudentId(id);
                    model.addAttribute("marks",marks);
                }
            }
           // StudentDTO student = studentService.getStudent(id);


        }catch (IllegalArgumentException e){
            System.out.println(e);
        }catch (StackOverflowError e){//StackOverflow
            System.out.println(e.getStackTrace());
        }
        return "/students/student";
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
    @GetMapping("/a/remove-kid/{id}/{idKid}")//dosnt work
    public String removeKid(@PathVariable("id") long id, @PathVariable("idKid") long idKid) {
        ParentDTO parent = parentService.getParent(id);
        Set<StudentDTO> kids = parent.getChildren();
        StudentDTO temp = new StudentDTO();
        for(StudentDTO kid : kids){
            if(kid.getId()==idKid){
                temp = kid;
            }
        }
        kids.remove(temp);
        parent.setChildren(kids);
        parentService.updateParent(id, modelMapper.map(parent, ParentDTO.class));
        return "redirect:/parents/{id}";
    }
    private ParentViewModel convertToViewmodel(ParentDTO parentDTO){
        return modelMapper.map(parentDTO, ParentViewModel.class);
    }
    @GetMapping("/{id}")//can be seen by teacher, admin
    public String viewParent(@PathVariable("id") long id, Model model) {
        ParentDTO parent=parentService.getParent(id);
        model.addAttribute("parent", parent);
        Set<StudentDTO> children = parent.getChildren();
        model.addAttribute("children",children);
        return "/parents/parent";
    }

    private  ParentViewModel convertToViewModel(ParentDTO parentDTO){
        return modelMapper.map(parentDTO, ParentViewModel.class);
    }
}
