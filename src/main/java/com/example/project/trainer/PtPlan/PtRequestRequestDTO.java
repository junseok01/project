package com.example.project.trainer.PtPlan;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PtRequestRequestDTO {
    private Long requestId;
    private UserEntity user;
    private TrainerEntity trainer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}
