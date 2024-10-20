package com.example.classbook1.data.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolTest {

    @InjectMocks
    private School school;

    @Mock
    private Director director;

    @Mock
    private Grade grade;

    @Mock
    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        school = new School();
    }

    @Test
    public void testSetName() {
        String name = "Test School";
        school.setName(name);
        assertEquals(name, school.getName());
    }

    @Test
    public void testSetAddress() {
        String address = "123 Main St";
        school.setAddress(address);
        assertEquals(address, school.getAddress());
    }

    @Test
    public void testSetDirector() {
        school.setDirector(director);
        assertEquals(director, school.getDirector());
    }

    @Test
    public void testSetDisabled() {
        school.setDisabled(true);
        assertTrue(school.isDisabled());
    }

    @Test
    public void testSetGrades() {
        Set<Grade> grades = new HashSet<>();
        grades.add(grade);
        school.setGrades(grades);
        assertEquals(grades, school.getGrades());
    }

    @Test
    public void testSetTeachers() {
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacher);
        school.setTeachers(teachers);
        assertEquals(teachers, school.getTeachers());
    }
}