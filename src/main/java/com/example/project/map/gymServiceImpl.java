package com.example.project.map;

import com.example.project.dto.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class gymServiceImpl implements gymService {
    private final gymDAO gymDAO;
    @Override
    public List<Gym> gymselectlist() {
        return gymDAO.gymselectlist();
    }
}
