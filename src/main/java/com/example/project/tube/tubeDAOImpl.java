package com.example.project.tube;

import com.example.project.dto.tube;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class tubeDAOImpl implements tubeDAO{
    private final tubeRepository tubeRepository;

    @Override
    public List<tube> tubeselectlist(String keyword,int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,6, Sort.by(Sort.Direction.DESC,"exercisenum"));
        return tubeRepository.findBySearch(keyword,pageRequest);
    }

    @Override
    public List<tube> tubecategorylist(String category,int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,6, Sort.by(Sort.Direction.DESC,"exercisenum"));
        return tubeRepository.findByCategory(category,pageRequest);
    }

    @Override
    public List<tube> tubelist(int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,6, Sort.by(Sort.Direction.DESC,"exercisenum"));
        Page<tube> list = tubeRepository.findAll(pageRequest);
        return list.getContent();
    }
}
