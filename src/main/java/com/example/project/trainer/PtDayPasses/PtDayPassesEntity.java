package com.example.project.trainer.PtDayPasses;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PtDayPasses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PtDayPassesEntity {
    @Id
    @GeneratedValue
    private Long requestId; // 요청 고유 번호

    @ManyToOne
    @JoinColumn(name = "loginName")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private TrainerEntity trainer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status;// 예약 상태 확인하는 객체(수락,거절)
    private int Price;
}
