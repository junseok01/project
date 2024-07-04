package com.example.project.heart;

import com.example.project.gym.GymBoardEntity;

import java.util.List;

public interface HeartService {
    public void heartGym(String loginId, Long gymBoardNum);
    public List<GymBoardEntity> getHeartList(String loginId);
    List<Long> getHeartedGymIds(String loginId);
}
