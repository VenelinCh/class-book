package com.example.classbook1.service;

import com.example.classbook1.data.entity.Director;
import com.example.classbook1.dto.DirectorDTO;
import java.util.List;

public interface DirectorService {
    List<DirectorDTO> getDirectors();
    Director create(DirectorDTO director);
    DirectorDTO getDirector(long id);
    Director updateDirector(long id, DirectorDTO director);
    void deleteDirector(long id);
    DirectorDTO getDirectorByname(String name);
}
