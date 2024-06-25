package com.example.project.tube;

import com.example.project.dto.tube;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class tubeDAOImpl implements tubeDAO{
    private final tubeRepository tubeRepository;

    @Override
    public List<tube> tubelist() {
        return tubeRepository.findAll();
    }

    @Override
    public List<tube> tubeselectlist(String keyword) {
        return tubeRepository.findBySearch(keyword);
    }

    @Override
    public List<tube> tubecategorylist(String category) {
        return tubeRepository.findByCategory(category);
    }
}
