package com.example.project.tube;

import com.example.project.dto.tube;

import java.util.List;

public interface tubeDAO {
    public List<tube> tubeselectlist(String keyword,int pageNo);
    public List<tube> tubecategorylist(String category,int pageNo);
    public List<tube> tubelist(int pageNo);
}
