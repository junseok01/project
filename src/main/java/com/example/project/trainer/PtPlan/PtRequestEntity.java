package com.example.project.trainer.PtPlan;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ptRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PtRequestEntity {
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

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    // Enum = 관련있는 상수들의 집합이며
    // JAVA에서는 final로 String, int  등을 나타내는 일정 개수의 기본 자료형의 값을 고정할 수 있다.

    public enum RequestStatus { // 요청보낸 상태
        pending, // 보류
        accepted, // 수락
        rejected // 거절
    }
}
