package com.example.project.map;

import com.example.project.dto.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface gymRepository extends JpaRepository<Gym,Long> {

    Page<Gym> findByGymaddrContaining(String gymaddr, Pageable pageRequest);
    Page<Gym> findByGymaddrOrGymnameContaining(String search1, String search2, Pageable pageRequest);

    @Query(
            value = "select * from gym g where g.x <= :x +1500 and g.x >= :x -1500 and g.y <= :y + 2000 and g.y >= :y - 2000",
            nativeQuery = true
    )
    List<Gym> AreaGym(float x, float y);
}
