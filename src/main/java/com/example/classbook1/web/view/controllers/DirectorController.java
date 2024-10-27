package com.example.classbook1.web.view.controllers;

import com.example.classbook1.dto.DirectorDTO;
import com.example.classbook1.dto.GradeDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.service.DirectorService;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.web.view.models.SchoolViewModel;
import com.example.classbook1.web.view.models.DirectorViewModel;
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
@RequestMapping("/directors")
public class DirectorController {
    DirectorService directorService;
    ModelMapper modelMapper;
    SchoolService schoolService;
    @GetMapping
    public String getDirectors(Model model){
        final List<DirectorViewModel> directors = directorService.getDirectors()
                .stream()
                .map(this::convertToViewmodel)
                .collect(Collectors.toList());
        model.addAttribute("directors", directors);
        return "/directors/directors";
    }
    @GetMapping("/a/create-director")
    public String showCreateSchoolForm(Model model) {
        model.addAttribute("director", new DirectorViewModel());
        List<SchoolDTO> schools = schoolService.getSchools();
        model.addAttribute("schools", schools);

        return "/directors/create-director";
    }

    //ne raboti
    @PostMapping("/a/create")
    public String createDirector(@ModelAttribute("director") DirectorViewModel director,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/directors/create-director";
        }
        System.out.println("===================================");
        System.out.println(director.getSchool().getName());
        directorService.create(modelMapper.map(director, DirectorDTO.class));
        return "redirect:/directors";
    }
    @GetMapping("/a/delete/{id}")
    public String deleteDirector(@PathVariable("id") long id) {
        directorService.deleteDirector(id);
        return "redirect:/directors";
    }
    @GetMapping("/a/edit/{id}")
    public String editDirector(@PathVariable("id") long id, Model model){
        DirectorDTO director = directorService.getDirector(id);
        model.addAttribute("director", director);
        List<SchoolDTO> schools = schoolService.getSchools();
        model.addAttribute("schools",schools);
        return "/directors/update-director";

    }
    @PostMapping("/a/update/{id}")
    public String updateDirector(@PathVariable("id") long id, @ModelAttribute("student") DirectorViewModel director,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/directors/edit-director";
        }
        directorService.updateDirector(id, modelMapper.map(director, DirectorDTO.class));
        return "redirect:/directors";
    }
    private DirectorViewModel convertToViewmodel(DirectorDTO directorDTO){
        return modelMapper.map(directorDTO, DirectorViewModel.class);
    }

}
