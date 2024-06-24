package com.example.project.trainer;

import java.util.List;

public interface TrainerDAO {
    void insert(TrainerEntity trainer);
    TrainerEntity read(long boardNo);
    void update(TrainerEntity dto);
    void delete(String boardNo);
    List<TrainerEntity> list();
    List<TrainerEntity> pagingFindAll();
    List<TrainerEntity> searchName(String trainerName);
}
