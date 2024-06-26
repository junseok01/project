package com.example.project.tube;


import com.example.project.dto.tubeResponseDTO;

import java.util.List;

public interface tubeService {
    public List<tubeResponseDTO> tubelist();
    public List<tubeResponseDTO> tubeselectlist(String keyword);
    public List<tubeResponseDTO> tubecategorylist(String category);
}
