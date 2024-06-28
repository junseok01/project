package com.example.project.trainer.PtPlan;

import com.example.project.trainer.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PtRequestRepository extends JpaRepository<PtRequestEntity,Long> {
    List<PtRequestEntity> findAllByTrainerName(TrainerEntity trainer);
}
