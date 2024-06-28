package com.example.project.trainer.PtPlan;

import java.util.List;

public interface PtRequestService {
    PtRequestResponseDTO createRequest(PtRequestRequestDTO requestDTO);
    // PT 요청
    PtRequestResponseDTO acceptRequest(Long requestId);
    // PT 수락
    PtRequestResponseDTO rejectRequest(Long requestId);
    // PT 거절
    List<PtRequestResponseDTO> getRequestsForTrainer(Long trainerId);
    // PT 요청목록
}
