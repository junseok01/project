package com.example.project.tube;


import com.example.project.dto.tubeResponseDTO;

import java.util.List;

public interface tubeService {
    public List<tubeResponseDTO> tubeselectlist(String keyword,int pageNo);
    public List<tubeResponseDTO> tubecategorylist(String category,int pageNo);
    public List<tubeResponseDTO> tubelist(int pageNo);
}
