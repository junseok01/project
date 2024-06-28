package com.example.project.trainer.PtPlan;

import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="ptPlan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PtPlanEntity {
    @Id
    @GeneratedValue
    private Long ptPlanId;
    @ManyToOne
    @JoinColumn(name = "loginName")
    private UserEntity userName; // (고객이름)님이 oo날 oo시간 pt 신청하셨습니다 등에 쓸 이름
    private LocalDateTime startTime; // pt시작시간
    private LocalDateTime endTime; // pt끝시간

}
