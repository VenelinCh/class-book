package com.example.classbook1.service;

import com.example.classbook1.data.entity.Program;

import java.util.List;
import java.util.stream.Collectors;

public interface ProgramService {
    public List<Program> getPrograms();
    public Program create(Program program);
    public Program getProgram(long id);
    public Program updateProgram(long id, Program updated);
    public void deleteProgram(long id);
}
