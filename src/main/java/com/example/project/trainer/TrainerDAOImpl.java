package com.example.project.trainer;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public TrainerEntity read(Long boardNo) {
        return repository.findById(boardNo).get();
    }

    @Override
    public void update(TrainerEntity dto) {

    }

    @Override
    public void delete(Long boardNo) {
        //영속성을 받기위해서 boardNo값을 넘겨줌
        TrainerEntity trainerEntity = repository.findById(boardNo).get();
        repository.delete(trainerEntity);
    }

    @Override
    public List<TrainerEntity> list() {
        return repository.findAll();
    }

    @Override
    public List<TrainerEntity> pagingFindAll() {
        PageRequest pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "boardNo"));
        Page<TrainerEntity> page = repository.findAll(pageRequest);
        System.out.println(page.getContent());
        return page.getContent();
    }

    @Override
    public List<TrainerEntity> searchName(String trainerName) {
        return repository.findByNameContaining(trainerName);
    }

    @Override
    public Page<TrainerEntity> pagelist(int page, int size) {
        Pageable pageable =PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    @Override
    public List<TrainerEntity> searchName(String trainerName,int page) {
        PageRequest pageRequest = PageRequest.of(page,5, Sort.by(Sort.Direction.DESC, "boardNo"));
        Page<TrainerEntity> pagelist = repository.findByNameContaining(trainerName,pageRequest);
        return pagelist.getContent();
    }


}
