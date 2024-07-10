package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.login.UserEntity;

import java.util.List;

public interface RatingService {
    boolean hasUserRated(Long gymId, String username);
    void saveRating(Long gymId, int score, String username);
}
