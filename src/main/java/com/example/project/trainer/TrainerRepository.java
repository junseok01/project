package com.example.project.trainer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity,String> {
    Page<TrainerEntity> findByNameContaining(String trainerName,Pageable pageRequest);
    List<TrainerEntity> findByNameContaining(String trainerName);
    // 트레이너 이름으로 검색
    TrainerEntity findByName(String name);
}
