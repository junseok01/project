package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GymBoardService {
    List<GymBoardResponseDTO> gymList();
    GymBoardEntity getgymInfo(Long gymboardnum);
    int update(TrainerEntity board);
    void delete(Long boardNo);
    List<GymBoardResponseDTO> pagingFindAll();
    List<GymBoardResponseDTO> findBygym(String gymname, int page);
    Page<GymBoardEntity> getgym(int page, int size);
    Page<GymBoardEntity> getSearchTrainer(String gymname,int page, int size);

}
