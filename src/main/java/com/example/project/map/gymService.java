package com.example.project.map;

import com.example.project.dto.GymMapResponseDTO;

import java.util.List;

public interface gymService {
    public List<GymMapResponseDTO> gymlist();
    public List<GymMapResponseDTO> gymselectlist(String keyword);
}
