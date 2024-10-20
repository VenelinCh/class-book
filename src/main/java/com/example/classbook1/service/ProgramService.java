package com.example.classbook1.service;

import com.example.classbook1.data.entity.Program;

import java.util.List;
import java.util.stream.Collectors;

public interface ProgramService {
     List<Program> getPrograms();
     Program create(Program program);
    Program getProgram(long id);
    Program updateProgram(long id, Program updated);
    void deleteProgram(long id);
}
