package com.example.project.trainer.PtPlan;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PtRequestServiceImpl implements PtRequestService {
    @Autowired
    PtPlanRepository ptPlanRepository;
    @Autowired
    PtRequestRepository ptRequestRepository;

    @Override
    public PtRequestResponseDTO createRequest(PtRequestRequestDTO requestDTO) {
        UserEntity userEntity = requestDTO.getUser();
        TrainerEntity trainerEntity = requestDTO.getTrainer();
        LocalDateTime start = requestDTO.getStartTime();
        LocalDateTime end = requestDTO.getEndTime();

        PtRequestEntity request = new PtRequestEntity();
        request.setUser(userEntity);
        request.setTrainer(trainerEntity);
        request.setStartTime(start);
        request.setEndTime(end);
        request.setStatus(PtRequestEntity.RequestStatus.pending);
        // 저장한 request 를 response로 변환하여 반환
        PtRequestEntity savedRequest = ptRequestRepository.save(request);
        return PtRequestResponseDTO.builder()
                .requestId(savedRequest.getRequestId())
                .userName(savedRequest.getUser().getName())
                .trainerName(savedRequest.getTrainer().getName())
                .startTime(savedRequest.getStartTime())
                .endTime(savedRequest.getEndTime())
                .status(String.valueOf(savedRequest.getStatus()))
                .build();
    }

    @Override
    public PtRequestResponseDTO acceptRequest(Long requestId) {
        // 요청이 존재하지않으면 기본값 null 반환
        PtRequestEntity request = ptRequestRepository.findById(requestId)
                .orElse(null);
        if (request == null) {
            System.out.println("요청 = null"); //오류체크
            return null;
        }
        // PT 요청 수락 (PT 요청 정보 저장)
        request.setStatus(PtRequestEntity.RequestStatus.accepted);
        PtPlanEntity ptPlan = new PtPlanEntity();
        ptPlan.setUserName(request.getUser());
        ptPlan.setStartTime(request.getStartTime());
        ptPlan.setEndTime(request.getEndTime());
        ptPlanRepository.save(ptPlan);
        // PT 요청 수락으로 변경하고 저장한 정보 response로 변환
        PtRequestEntity savedRequest = ptRequestRepository.save(request);
        return PtRequestResponseDTO.builder()
                .requestId(savedRequest.getRequestId())
                .userName(savedRequest.getUser().getName())
                .startTime(savedRequest.getStartTime())
                .endTime(savedRequest.getEndTime())
                .status(String.valueOf(savedRequest.getStatus()))
                .build();
    }

    @Override
    public PtRequestResponseDTO rejectRequest(Long requestId) {
        PtRequestEntity request = ptRequestRepository.findById(requestId)
                .orElse(null);

        if (request == null) {
            System.out.println("요청 null인 상태");
            return null;
        }
        // PT 요청 거절
        request.setStatus(PtRequestEntity.RequestStatus.rejected);
        // response로 반환
        PtRequestEntity savedRequest = ptRequestRepository.save(request);
        return PtRequestResponseDTO.builder()
                .requestId(savedRequest.getRequestId())
                .userName(savedRequest.getUser().getName())
                .startTime(savedRequest.getStartTime())
                .endTime(savedRequest.getEndTime())
                .status(String.valueOf(savedRequest.getStatus()))
                .build();
    }

    @Override
    public List<PtRequestResponseDTO> getRequestsForTrainer(Long trainerId) {
        TrainerEntity trainer = new TrainerEntity();
        trainer.setBoardNo(trainerId);
        // 트레이너에게 온 PT요청
        List<PtRequestEntity> requests = ptRequestRepository.findAllByTrainerName(trainer);
        // response로 반환
        return requests.stream()
                .map(request -> PtRequestResponseDTO.builder()
                        .requestId(request.getRequestId())
                        .userName(request.getUser().getName())
                        .trainerName(request.getTrainer().getName())
                        .startTime(request.getStartTime())
                        .endTime(request.getEndTime())
                        .status(String.valueOf(request.getStatus()))
                        .build())
                        .collect(Collectors.toList());
    }
}
