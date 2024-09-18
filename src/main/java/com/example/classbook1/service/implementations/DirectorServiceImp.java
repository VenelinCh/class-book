package com.example.classbook1.service.implementations;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.data.repository.DirectorRepository;
import com.example.classbook1.dto.DirectorDTO;
import com.example.classbook1.service.DirectorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DirectorServiceImp implements DirectorService {
    private final DirectorRepository directorRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<DirectorDTO> getDirectors(){
        return directorRepository.findAll().stream()
                .map(this::convertToDirectorDTO)
                .collect(Collectors.toList());
    }
    private DirectorDTO convertToDirectorDTO(Director director){
        return modelMapper.map(director, DirectorDTO.class);
    }
    @Override
    public Director create(DirectorDTO director){
        return directorRepository.save(modelMapper.map(director, Director.class));
    }
    @Override
    public DirectorDTO getDirector(long id){
        return modelMapper.map(directorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid director Id:" + id)), DirectorDTO.class);
    }
    public Director updateDirector(long id, DirectorDTO directorDTO){
        Director director = modelMapper.map(directorDTO, Director.class);
        director.setId(id);
        return directorRepository.save(director);
    }
    public void deleteDirector(long id){
        directorRepository.deleteById(id);
    }
    public DirectorDTO getDirectorByname(String name){
        return modelMapper.map(directorRepository.findDirectorByName(name), DirectorDTO.class);
    }
    public DirectorDTO getDirectorBynameAndLastName(String name, String lastName){
        return modelMapper.map(directorRepository.findDirectorByNameAndLastName(name, lastName), DirectorDTO.class);
    }
}
