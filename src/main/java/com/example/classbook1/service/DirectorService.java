package com.example.classbook1.service;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.dto.DirectorDTO;
import java.util.List;

public interface DirectorService {
    public List<DirectorDTO> getDirectors();
    public  Director create(DirectorDTO director);
    public  DirectorDTO getDirector(long id);
    public Director updateDirector(long id, DirectorDTO director);
    public void deleteDirector(long id);
    public DirectorDTO getDirectorByname(String name);
}
