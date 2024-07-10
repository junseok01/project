package com.example.project.trainer.PtDayPasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PtDayPassesResponseDTO {
    private Long requestId;
    private String user;
    private String trainer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public PtDayPassesResponseDTO(PtDayPassesEntity pt) {
        this.requestId = pt.getRequestId();
        this.user = pt.getUser().getName();
        this.trainer = pt.getTrainer().getTrainerId();
        this.startTime = pt.getStartTime();
        this.endTime = pt.getEndTime();
        this.status = pt.getStatus();
    }
}
