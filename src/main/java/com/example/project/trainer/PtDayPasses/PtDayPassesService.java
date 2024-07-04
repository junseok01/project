package com.example.project.trainer.PtDayPasses;

import java.time.LocalDateTime;
import java.util.List;

public interface PtDayPassesService {
    void reservePt(PtDayPassesRequestDTO requestDTO);

    List<PtDayPassesResponseDTO> getAllPtDayPasses();

    void  acceptPtDayPasses(PtDayPassesRequestDTO requestDTO);

    void rejectPtDayPasses(PtDayPassesRequestDTO requestDTO);

    PtDayPassesEntity reservationCheck(String trainerName, LocalDateTime startTime, LocalDateTime endTime);
}
