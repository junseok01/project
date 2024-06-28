package com.example.project.trainer;

import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainerDAO {
    void insert(TrainerEntity trainer);
    TrainerEntity read(long boardNo);
    void update(TrainerEntity dto);
    void delete(Long boardNo);
    List<TrainerEntity> list();
    List<TrainerEntity> pagingFindAll();
    List<TrainerEntity> searchName(String trainerName);
    Page<TrainerEntity> pagelist(int page, int size);
    List<TrainerEntity> searchName(String trainerName,int page);
}
