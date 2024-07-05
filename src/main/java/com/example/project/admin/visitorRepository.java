package com.example.project.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface visitorRepository extends JpaRepository<visitorEntity,Long> {
    Optional<visitorEntity> findByVisitDate(LocalDate visitDate);
    List<visitorEntity> findAllByOrderByVisitDateAsc();
}
