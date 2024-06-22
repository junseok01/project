package com.example.project.map;

import com.example.project.dto.Gym;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface gymRepository extends JpaRepository<Gym,Long> {
    List<Gym> findByGymaddrContaining(String gymaddr, Pageable pageRequest);
    List<Gym> findByGymaddrOrGymnameContaining(String search1, String search2, Pageable pageRequest);
}
