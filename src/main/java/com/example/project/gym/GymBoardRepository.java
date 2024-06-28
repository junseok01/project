package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymBoardRepository extends JpaRepository<GymBoardEntity,Long> {
    Page<GymBoardEntity> findByGymnameContaining(String gymname, Pageable pageRequest);
}
