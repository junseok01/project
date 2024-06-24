package com.example.project.trainer;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.List;
@RequiredArgsConstructor
@Repository
public class TrainerDAOImpl implements TrainerDAO{
    private final TrainerRepository repository;

    @Override
    public void  insert(TrainerEntity trainer) {
        repository.save(trainer);
    }

    @Override
    public TrainerEntity read(long boardNo) {
        return repository.findById(boardNo).get();
    }

    @Override
    public void update(TrainerEntity dto) {

    }

    @Override
    public void delete(String boardNo) {

    }

    @Override
    public List<TrainerEntity> list() {
        return repository.findAll();
    }

    @Override
    public List<TrainerEntity> pagingFindAll() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "boardNo"));
        Page<TrainerEntity> page = repository.findAll(pageRequest);
        System.out.println(page.getContent());
        return page.getContent();
    }


}
