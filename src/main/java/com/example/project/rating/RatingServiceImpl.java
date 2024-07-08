package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.gym.GymBoardRepository;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import com.example.project.trainer.PtDayPasses.PtDayPassesEntity;
import com.example.project.trainer.TrainerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final GymBoardRepository gymBoardRepository;
    public boolean hasUserRated(Long gymId, String username) {
        return ratingRepository.existsByGymInfoAndUsername(gymId, username);
    }

    public void saveRating(Long gymId, int score, String username) {
        RatingEntity ratingEntity = new RatingEntity();
        System.out.println(gymId+score+username+"==================================================================");
        ratingEntity.setGymInfo(gymId);
        ratingEntity.setScore(score);
        ratingEntity.setUsername(username);
        ratingRepository.save(ratingEntity);
    }
}
