package com.example.project.trainer.PtDayPasses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PtDayPassesRepository extends JpaRepository<PtDayPassesEntity , Long> {
    List<PtDayPassesEntity> findAll();
    PtDayPassesEntity findByTrainerNameAndStartTimeOrEndTime(String trainerName, LocalDateTime startTime, LocalDateTime endTime);
}
