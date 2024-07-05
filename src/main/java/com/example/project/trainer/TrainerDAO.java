package com.example.project.trainer;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TrainerDAO {
    void insert(TrainerEntity trainer, MultipartFile file) throws IOException;
    TrainerEntity read(Long boardNo);
    void update(TrainerEntity dto);
    void delete(Long boardNo);
    List<TrainerEntity> list();
    List<TrainerEntity> pagingFindAll();
    List<TrainerEntity> searchName(String trainerName);
    Page<TrainerEntity> pagelist(int page, int size);
    List<TrainerEntity> searchName(String trainerName,int page);
    Page<TrainerEntity> searchName(String trainerName,int page ,int size);
}
