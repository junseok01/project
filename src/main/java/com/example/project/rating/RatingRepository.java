package com.example.project.rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity,Long> {
    boolean existsByGymInfoAndUsername(Long gymInfo, String username);
}
