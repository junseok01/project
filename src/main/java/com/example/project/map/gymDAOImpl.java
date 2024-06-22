package com.example.project.map;

import com.example.project.dto.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class gymDAOImpl implements gymDAO{
    private final gymRepository repository;
    @Override
    public List<Gym> gymlist() {
        List<Gym> list = repository.findAll();
        return list;
    }

    @Override
    public List<Gym> gymselectlist(String keyword) {
        PageRequest pageRequest = PageRequest.of(0,15, Sort.by(Sort.Direction.ASC,"gymnumber"));
        List<Gym> list = repository.findByGymaddrOrGymnameContaining(keyword,keyword, pageRequest);
        return list;
    }

    @Override
    public List<Gym> gymselectaddrlist(String keyword) {
        PageRequest pageRequest = PageRequest.of(0,15, Sort.by(Sort.Direction.ASC,"gymnumber"));
        return repository.findByGymaddrContaining(keyword,pageRequest);
    }

}
