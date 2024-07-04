package com.example.project.heart;

import com.example.project.gym.GymBoardEntity;
import com.example.project.gym.GymBoardRepository;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeartServiceImpl implements HeartService{
    UserRepository userRepository;
    GymBoardRepository gymBoardRepository;

    public HeartServiceImpl(UserRepository userRepository, GymBoardRepository gymBoardRepository) {
        this.userRepository = userRepository;
        this.gymBoardRepository = gymBoardRepository;
    }

    //찜버튼을 눌렀을때
    @Transactional
    public void heartGym(String loginId, Long gymBoardNum) {
        UserEntity user = userRepository.findById(loginId).get();
        GymBoardEntity gymBoard = gymBoardRepository.findById(gymBoardNum).get();
        System.out.println(user.getHeartedGyms() + " --------------");
        if(!user.getHeartedGyms().contains(gymBoard)){
            System.out.println("엔티티내에 gym보드 존재하지않음 하트추가작업 진행");
            user.heartGym(gymBoard);
        }else{
            System.out.println("엔티티내에 gym보드 존재함 하트 삭제작업 진행");
            user.unheartGym(gymBoard);
        }
        System.out.println(user.getHeartedGyms());


        userRepository.save(user);
    }

    @Transactional
    public List<GymBoardEntity> getHeartList(String loginId){
        UserEntity user = userRepository.findByLoginId(loginId);
        return user.getHeartedGyms();
    }
    @Transactional
    public List<Long> getHeartedGymIds(String loginId) {
        UserEntity user = userRepository.findByLoginId(loginId);
        if (user != null) {
            return user.getHeartedGyms().stream()
                    .map(GymBoardEntity::getGymboardnum)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("User not found");
    }
}
