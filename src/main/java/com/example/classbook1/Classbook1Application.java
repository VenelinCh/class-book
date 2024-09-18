package com.example.classbook1;

import com.example.classbook1.data.entity.Grade;
import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.entity.Teacher;
import com.example.classbook1.data.repository.SchoolRepository;
import com.example.classbook1.data.repository.TeacherRepository;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.service.SchoolService;
import com.example.classbook1.service.implementations.SchoolServiceImp;
import com.example.classbook1.service.implementations.StudentServiceImp;
import com.example.classbook1.web.view.models.StudentViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Classbook1Application {

	public static void main(String[] args) {
		SpringApplication.run(Classbook1Application.class, args);
//		Example example = null;
//		Grade grade = new Grade();
//		grade.setGrade(7);
//		grade.setLabel("a");
//		StudentViewModel studentViewModel = new StudentViewModel();
//		studentViewModel.setName("Example1");
//		studentViewModel.setGrade(grade);
//		example.createStudent(studentViewModel);
		}

}
