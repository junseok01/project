package com.example.project.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<VisitorEntity,Long> {
    Optional<VisitorEntity> findByVisitDate(LocalDate visitDate);
    List<VisitorEntity> findAllByOrderByVisitDateAsc();
    List<VisitorEntity> findAllByVisitDateBetween(LocalDate startDate, LocalDate endDate);

}
