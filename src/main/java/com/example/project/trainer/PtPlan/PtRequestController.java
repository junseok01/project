package com.example.project.trainer.PtPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PtRequestController {

    private final PtRequestService ptRequestService;
    @GetMapping("trainerPTpage")
    public String trainerPTpage(){
        return "mypage/trainerPTpage";
    }
    // PT 요청 생성 및 정보
    @PostMapping
    public PtRequestResponseDTO createRequest(@RequestBody PtRequestRequestDTO requestDTO) {
        return ptRequestService.createRequest(requestDTO);
    }
    @PutMapping("/{requestId}") // 요청정보를 수락으로 변경
    public PtRequestResponseDTO acceptRequest(@PathVariable Long requestId) {
        return ptRequestService.acceptRequest(requestId);
    }
    @PutMapping("/{requestId}/reject") // 요청정보를 거절로 변경
    public PtRequestResponseDTO rejectRequest(@PathVariable Long requestId) {
        return ptRequestService.rejectRequest(requestId);
    }
    @GetMapping("/trainer/{trainerId}") // PT목록 보기
    public List<PtRequestResponseDTO> getRequestForTrainer(@PathVariable Long trainerId) {
        return ptRequestService.getRequestsForTrainer(trainerId);
    }
}
