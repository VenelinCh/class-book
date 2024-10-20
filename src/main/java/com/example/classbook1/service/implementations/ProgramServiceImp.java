package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Program;
import com.example.classbook1.data.repository.ProgramRepository;
import com.example.classbook1.service.ProgramService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProgramServiceImp implements ProgramService {
    private final ProgramRepository programRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Program> getPrograms(){
        return programRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Program create(Program program){
        return programRepository.save(program);
    }
    @Override
    public Program getProgram(long id){
        return modelMapper.map(programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid program Id:" + id)), Program.class);
    }
    @Override
    public Program updateProgram(long id, Program updated){
        Program program = updated;
        program.setId(id);
        return programRepository.save(program);
    }
    @Override
    public void deleteProgram(long id){
        programRepository.deleteById(id);
    }

}
