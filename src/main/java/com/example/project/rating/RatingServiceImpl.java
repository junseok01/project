package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository repository;
    public RatingEntity saveRating(RatingEntity rating) {
        return repository.save(rating);
    }
}
