package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.School;
import com.example.classbook1.data.repository.SchoolRepository;
import com.example.classbook1.dto.CreateSchoolDTO;
import com.example.classbook1.dto.SchoolDTO;
import com.example.classbook1.dto.UpdateSchoolDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchoolServiceImpTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SchoolServiceImp schoolService;

    private School school;
    private SchoolDTO schoolDTO;
    private CreateSchoolDTO createSchoolDTO;

    @BeforeEach
    void setUp() {
        school = new School();
        school.setId(1L);
        school.setName("Test School");

        schoolDTO = new SchoolDTO();
        schoolDTO.setId(1L);
        schoolDTO.setName("Test School");

        createSchoolDTO = new CreateSchoolDTO();
        createSchoolDTO.setName("Test School");
    }

    @Test
    void testGetSchools() {
        when(schoolRepository.findAll()).thenReturn(Collections.singletonList(school));
        when(modelMapper.map(school, SchoolDTO.class)).thenReturn(schoolDTO);

        List<SchoolDTO> schools = schoolService.getSchools();
        assertEquals(1, schools.size());
        assertEquals(schoolDTO, schools.get(0));
    }

    @Test
    void testCreateSchool() {
        when(schoolRepository.save(any(School.class))).thenReturn(school);
        when(modelMapper.map(createSchoolDTO, School.class)).thenReturn(school);

        School createdSchool = schoolService.create(createSchoolDTO);
        assertEquals(school, createdSchool);
    }

    @Test
    void testGetSchool() {
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(school));
        when(modelMapper.map(school, SchoolDTO.class)).thenReturn(schoolDTO);

        SchoolDTO foundSchool = schoolService.getSchool(1L);
        assertEquals(schoolDTO, foundSchool);
    }
    @Test
    void testUpdateSchool() {
        long id = 1L;
        UpdateSchoolDTO updateSchoolDTO = new UpdateSchoolDTO();
        updateSchoolDTO.setName("Updated School");

        School school = new School();
        school.setId(id);
        school.setName("Updated School");

        when(modelMapper.map(updateSchoolDTO, School.class)).thenReturn(school);
        when(schoolRepository.save(school)).thenReturn(school);

        School updatedSchool = schoolService.updateSchool(id, updateSchoolDTO);
        assertEquals(school, updatedSchool);
    }
    @Test
    void testDeleteSchool() {
        long id = 1L;

        doNothing().when(schoolRepository).deleteById(id);

        schoolService.deleteSchool(id);
        verify(schoolRepository, times(1)).deleteById(id);
    }
    @Test
    void testGetSchoolByName() {
        String name = "Test School";

        School school = new School();
        school.setId(1L);
        school.setName(name);

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setId(1L);
        schoolDTO.setName(name);

        when(schoolRepository.findSchoolByName(name)).thenReturn(school);
        when(modelMapper.map(school, SchoolDTO.class)).thenReturn(schoolDTO);

        SchoolDTO foundSchool = schoolService.getSchoolByname(name);
        assertEquals(schoolDTO, foundSchool);
    }


}