package com.example.project.trainer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity,Long> {
    Page<TrainerEntity> findByNameContaining(String trainerName,Pageable pageRequest);
    List<TrainerEntity> findByNameContaining(String trainerName);
}
