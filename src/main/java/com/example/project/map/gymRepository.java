package com.example.project.map;

import com.example.project.dto.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface gymRepository extends JpaRepository<Gym,Long> {

}
