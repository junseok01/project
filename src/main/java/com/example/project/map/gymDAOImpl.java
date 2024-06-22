package com.example.project.map;

import com.example.project.dto.Gym;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class gymDAOImpl implements gymDAO{
    private final gymRepository repository;
    @Override
    public List<Gym> gymselectlist() {
        List<Gym> list = repository.findAll();
        return list;
    }
}
