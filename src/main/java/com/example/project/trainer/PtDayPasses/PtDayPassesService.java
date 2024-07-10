package com.example.project.trainer.PtDayPasses;

import com.example.project.login.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDateTime;
import java.util.List;

public interface PtDayPassesService {
    String reservePt(PtDayPassesRequestDTO requestDTO);

    List<PtDayPassesResponseDTO> getAllPtDayPasses();

    List<PtDayPassesResponseDTO> getPtDayPassesByTrainer(String trainerId);

    void acceptPtDayPasses(PtDayPassesRequestDTO requestDTO);

    void rejectPtDayPasses(PtDayPassesRequestDTO requestDTO);

    PtDayPassesEntity reservationCheck(String trainerName, LocalDateTime startTime, LocalDateTime endTime);

    PtDayPassesEntity getPtDayPassById(Long requestId);
}
