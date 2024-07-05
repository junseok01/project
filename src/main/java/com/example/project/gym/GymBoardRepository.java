package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GymBoardRepository extends JpaRepository<GymBoardEntity,Long> {
    Page<GymBoardEntity> findByGymnameContaining(String gymname, Pageable pageRequest);
    Page<GymBoardEntity> findByGymaddrContaining(String gymaddr,Pageable pageRequest);

}
