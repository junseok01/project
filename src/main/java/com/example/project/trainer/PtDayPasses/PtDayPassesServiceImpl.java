package com.example.project.trainer.PtDayPasses;

import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import com.example.project.trainer.TrainerEntity;
import com.example.project.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PtDayPassesServiceImpl implements PtDayPassesService {
    @Autowired
    private PtDayPassesRepository PtDayPassesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public void reservePt(PtDayPassesRequestDTO requestDTO) {

        UserEntity user = userRepository.findByLoginId(requestDTO.getUser());
        TrainerEntity trainer =trainerRepository.findByName(requestDTO.getTrainer());

        PtDayPassesEntity ptDayPasses = PtDayPassesEntity.builder()
                .user(user)
                .trainer(trainer)
                .startTime(requestDTO.getStartTime())
                .endTime(requestDTO.getEndTime())
                .build();
        System.out.println("service===>"+ptDayPasses);
        PtDayPassesRepository.save(ptDayPasses);
    }

    @Override
    public List<PtDayPassesResponseDTO> getAllPtDayPasses() {
        List<PtDayPassesEntity> ptDayPasses = PtDayPassesRepository.findAll();
        return ptDayPasses.stream()
                .map(ptDayPass -> new PtDayPassesResponseDTO(ptDayPass.getRequestId()
                , ptDayPass.getUser().getName(),ptDayPass.getTrainer().getName()
                ,ptDayPass.getStartTime(),ptDayPass.getEndTime(),ptDayPass.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void acceptPtDayPasses(PtDayPassesRequestDTO requestDTO) {
        PtDayPassesEntity ptDayPassesEntity = PtDayPassesRepository.findById(requestDTO.getRequestId())
                .orElse(null);
        if(ptDayPassesEntity != null) {
            ptDayPassesEntity.setStatus("accept");
            PtDayPassesRepository.save(ptDayPassesEntity);
        }
    }

    @Override
    public void rejectPtDayPasses(PtDayPassesRequestDTO requestDTO) {
        PtDayPassesEntity ptDayPassesEntity = PtDayPassesRepository.findById(requestDTO.getRequestId())
                .orElse(null);
        if(ptDayPassesEntity != null) {
            ptDayPassesEntity.setStatus("reject");
            PtDayPassesRepository.save(ptDayPassesEntity);
        }
    }

    @Override
    public PtDayPassesEntity reservationCheck(String trainerName, LocalDateTime startTime, LocalDateTime endTime) {
        return PtDayPassesRepository.findByTrainerNameAndStartTimeOrEndTime(trainerName,startTime,endTime);
    }
}
