package com.example.project.map;

import com.example.project.dto.Gym;

import java.util.List;

public interface gymDAO {
    public List<Gym> gymlist(float x,float y);
    public List<Gym> gymselectlist(String keyword);
    public List<Gym> gymselectaddrlist(String keyword);
}
