package com.example.project.tube;

import com.example.project.dto.tube;

import java.util.List;

public interface tubeDAO {
    public List<tube> tubelist();
    public List<tube> tubeselectlist(String keyword);
    public List<tube> tubecategorylist(String category);
}
