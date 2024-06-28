package com.example.project.trainer.PtPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PtPlanRepository extends JpaRepository<PtPlanEntity,Long> {
}
