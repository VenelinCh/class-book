package com.example.classbook1.web.view.controllers;

import com.example.classbook1.data.entity.Subject;
import com.example.classbook1.dto.*;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.service.SubjectService;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    TeacherService teacherService;
    SubjectService subjectService;
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

        Set<SchoolDTO> schools = teacher.getSchools();
        model.addAttribute("schools",schools);

        Set <Subject > subjects = teacherService.getSubjectsThatCanTeach(teacher);
        model.addAttribute("subjects", subjects);
        //Subject addSubject = new Subject();

        List <Subject > allSubjects = subjectService.getSubjects();
        model.addAttribute("allSubjects", allSubjects);

        return "/teachers/update-teacher";

    }
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") long id, @ModelAttribute("teacher") TeacherViewModel teacher,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/teachers/edit-teacher";
        }
        TeacherDTO teacherDTO = new TeacherDTO();
        Set<SchoolDTO> schools = teacher.getSchools();
        if(schools == null){
            teacherDTO = teacherService.getTeacher(id);
            schools = teacherDTO.getSchools();
            teacher.setSchools(schools);
        }
        Set<Subject> subjects = teacher.getSubjectsThatCanTeach();
        if(subjects == null){
            subjects = teacherDTO.getSubjectsThatCanTeach();
            teacher.setSubjectsThatCanTeach(subjects);
        }
        String subjectToAdd  = teacher.getSubjectsStr();
        //teacher.setSubjectsStr("math");//remove
        String [] arrSubjects = separateStrings(subjectToAdd);
        for(String sub: arrSubjects){

            subjects.add(subjectService.getSubjectByName(sub));
        }
        teacher.setSubjectsThatCanTeach(subjects);
        teacherService.updateTeacher(id, modelMapper.map(teacher, UpdateTeacherDTO.class));
        return "redirect:/teachers/edit/{id}";
    }
    @GetMapping("/remove-school/{id}/{idSchool}")
    public String removeSchool(@PathVariable("id") long id, @PathVariable("idSchool") long idSchool) {
        TeacherDTO teacher = teacherService.getTeacher(id);
        Set<SchoolDTO> schools = teacher.getSchools();
        SchoolDTO temp = new SchoolDTO();
        for(SchoolDTO school : schools){
            if(school.getId()==idSchool){
                temp = school;
            }
        }
        schools.remove(temp);
        teacher.setSchools(schools);
        teacherService.updateTeacher(id, modelMapper.map(teacher, UpdateTeacherDTO.class));
        return "redirect:/teachers";
    }
    @GetMapping("/remove-subject/{id}/{idSubject}")
    public String removeSubject(@PathVariable("id") long id, @PathVariable("idSubject") long idSubject) {
        TeacherDTO teacher = teacherService.getTeacher(id);
        Set<Subject> subjects = teacher.getSubjectsThatCanTeach();
        Subject temp = new Subject();
        for(Subject subject : subjects){
            if(subject.getId()==idSubject){
                temp = subject;
            }
        }
        subjects.remove(temp);
        teacher.setSubjectsThatCanTeach(subjects);
        teacherService.updateTeacher(id, modelMapper.map(teacher, UpdateTeacherDTO.class));
        return "redirect:/teachers";
    }
    @PostMapping("/add-subject/{id}")
    public String addSubject(@PathVariable("id") long id, @ModelAttribute("addSubject") String addSubject,//Subject subject,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/teachers/edit-teacher";
        }
        TeacherDTO teacher = teacherService.getTeacher(id);
        Set<Subject> subjects = teacherService.getSubjectsThatCanTeach(teacher);

        //subjects.add(subject);
        subjects.add(subjectService.getSubjectByName(addSubject));
        System.out.println("++++++++++++++++++++++++++++++++++++++++" + subjectService.getSubjectByName(addSubject) + "Subject:" + addSubject);
        teacherService.updateTeacher(id, modelMapper.map(teacher, UpdateTeacherDTO.class));
        return "redirect:/teachers";
    }
    public static String [] separateStrings(String text){
        int len = text.length();
        char text1[] = new char[len] ;
        int indexNew=0;
        if(text==null) return null;

        int countWords=1;
        for(int i=0;i<len;i++){
            if(text.charAt(i)==','){
                countWords++;
            }
        }
        String [] words = new String[countWords];
        int cnt=0;
        for(int i=0;i<len;i++){
            int num = text.charAt(i);
            if((num >=48 && num <=57) || (num>=65 && num<=90) || (num >=97 && num<=122) || text.charAt(i)==' '){
                text1[indexNew++] = text.charAt(i);
            }
            else if(text.charAt(i)==','){
                words[cnt++] = String.valueOf(text1,0,indexNew);
                indexNew=0;
            }
        }
        words[cnt++] = String.valueOf(text1,0,indexNew);
        indexNew=0;
        return words;
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
