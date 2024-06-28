package com.example.project.trainer.PtPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PtRequestResponseDTO {
    private Long requestId;
    private String userName;
    private String trainerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}
