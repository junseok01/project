package com.example.project.map;

import com.example.project.dto.Gym;
import com.example.project.dto.GymMapResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class gymServiceImpl implements gymService {
    private final gymDAO dao;
    @Override
    public List<GymMapResponseDTO> gymselectlist() {
        List<Gym> list = dao.gymselectlist();
        List<GymMapResponseDTO> gymlst = list.stream()
                .map(GymMapResponseDTO :: new)
                .collect(Collectors.toList());
        return gymlst;
    }
}
